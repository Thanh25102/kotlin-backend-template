package com.khipster.template.khipstertemplate.modules.smartcontrol

import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.io.ByteArrayInputStream

@RestController
@RequestMapping("")
class ReverseProxyResource(
    private val restTemplate: RestTemplate,
    private val tokenManagement: TokenManagement,
) {

    @Value("\${third-party.vms-api.url}")
    private lateinit var vmsBaseUrl: String

    @GetMapping("/proxy/{filename:.+\\.m3u8}", produces = ["application/vnd.apple.mpegurl"])
    fun proxyM3U8(@PathVariable filename: String): ResponseEntity<String> {
        println("Proxying $filename")
        val token = tokenManagement.getToken()
        val targetUrl = "$vmsBaseUrl/api/camera/$filename"

        val uri = UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("token", token)
            .toUriString()

        println("uri: $uri")
        val response = restTemplate.getForEntity(uri, String::class.java)
        if (response.statusCode != HttpStatus.OK || response.body == null) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build()
        }
        val m3u8Content = response.body!!

        val updatedContent = m3u8Content
            .replace(Regex("""URI="([^"]+)"""")) { matchResult ->
                val originalUri = matchResult.groupValues[1].trim()
                """URI="/proxy/$originalUri""""
            }
            .replace(Regex("""^(?!#)(.*\.m4s)""", RegexOption.MULTILINE)) { matchResult ->
                val originalSegment = matchResult.groupValues[1].trim()
                "/proxy/$originalSegment"
            }

        println("updatedContent: $updatedContent")
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, "application/vnd.apple.mpegurl")
            .body(updatedContent)
    }

    @GetMapping("/proxy/{fileName:^(?!.*\\.m3u8$).+}")
    fun proxySegment(@PathVariable fileName: String,response: HttpServletResponse){
        println("Proxying2 $fileName")
        val token = tokenManagement.getToken()
        val targetUrl = "$vmsBaseUrl/api/camera/$fileName?token=$token"

        val uri = UriComponentsBuilder.fromHttpUrl(targetUrl)
            .queryParam("token", token)
            .toUriString()

        val contentType = when {
            fileName.endsWith(".m4s", ignoreCase = true) -> "video/mp4"
            fileName.endsWith(".mp4", ignoreCase = true) -> "video/mp4"
            fileName.endsWith(".ts", ignoreCase = true) -> "video/mp2t"
            else -> "application/octet-stream"
        }

        val segmentResponse  = restTemplate.getForEntity(uri, ByteArray::class.java)
        if (segmentResponse .statusCode != HttpStatus.OK || segmentResponse .body == null) {
            response.status = HttpServletResponse.SC_BAD_GATEWAY
            return
        }
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type")

        response.contentType = contentType
        response.outputStream.write(segmentResponse.body)
        response.outputStream.flush()

    }
}

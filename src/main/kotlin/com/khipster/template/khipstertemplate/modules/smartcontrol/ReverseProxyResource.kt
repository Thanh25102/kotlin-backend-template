package com.khipster.template.khipstertemplate.modules.smartcontrol

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
        val token = tokenManagement.getToken()
        val targetUrl = "$vmsBaseUrl/camera/$filename"

        val uri = UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("token", token)
            .toUriString()

        println("TargetUrl: $uri")

        val response = restTemplate.getForEntity(uri, String::class.java)
        if (response.statusCode != HttpStatus.OK || response.body == null) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build()
        }

        val m3u8Content = response.body!!

        val updatedContent = m3u8Content
            .replace(Regex("""URI=\"([^\"]+)\""")) { matchResult ->
                val originalUri = matchResult.groupValues[1]
                """URI=\"/proxy/$originalUri\"""
            }
            .replace(Regex("""^(?!#)(.*\\.m4s)""", RegexOption.MULTILINE)) { matchResult ->
                val originalSegment = matchResult.groupValues[1]
                "/proxy/$originalSegment"
            }

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, "application/vnd.apple.mpegurl")
            .body(updatedContent)
    }

    @GetMapping("/proxy/{fileName:.+}")
    fun proxySegment(@PathVariable fileName: String): ResponseEntity<ByteArrayInputStream> {
        val token = tokenManagement.getToken()
        val targetUrl = "$vmsBaseUrl/camera/$fileName"

        val uri = UriComponentsBuilder.fromHttpUrl(targetUrl)
            .queryParam("token", token)
            .toUriString()

        val contentType = when {
            fileName.endsWith(".m4s", ignoreCase = true) -> "video/mp4"
            fileName.endsWith(".mp4", ignoreCase = true) -> "video/mp4"
            fileName.endsWith(".ts", ignoreCase = true) -> "video/mp2t"
            else -> "application/octet-stream"
        }

        val response = restTemplate.getForEntity(uri, ByteArray::class.java)
        if (response.statusCode != HttpStatus.OK || response.body == null) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build()
        }

        val inputStream = ByteArrayInputStream(response.body!!)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType)
            .body(inputStream)
    }
}

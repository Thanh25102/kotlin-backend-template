package com.khipster.template.khipstertemplate

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter

@SpringBootTest
class KhipsterTemplateApplicationTests {

    private val modules = ApplicationModules.of(KhipsterTemplateApplication::class.javaObjectType)

    @Test
    fun contextLoads() {
    }

    @Test
    fun writeDocumentationSnippets() {
        Documenter(modules)
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml()
    }

    @Test
    fun createApplicationModuleModel() {
        modules.forEach { println(it) }
        modules.verify()
    }

}

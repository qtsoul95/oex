package com.example.oex.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(scanBasePackageClasses = [WebApplication::class])
@EnableTransactionManagement
class WebApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(WebApplication::class.java)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(WebApplication::class.java, *args)
        }
    }
}

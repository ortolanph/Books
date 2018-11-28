package org.books

import org.books.interceptors.AuthorizationInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    @Bean
    fun authorizationInterceptor() : AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry!!
                .addInterceptor(authorizationInterceptor())
                .excludePathPatterns("/api/public")
    }

}

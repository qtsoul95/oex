package com.example.oex.web.config

import com.example.oex.web.services.security.CustomizedAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.NegatedRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class WebSecurityConfig(
    private val authenticationProvider: CustomizedAuthenticationProvider
) : WebSecurityConfigurerAdapter() {

    private val publicUrls = OrRequestMatcher(
        AntPathRequestMatcher("/public/**")
    )

    private val protectedUrls = NegatedRequestMatcher(publicUrls)

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/api/v1/**",
            "/sample/**"
        )
    }

    override fun configure(http: HttpSecurity?) {
        http!!
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), protectedUrls)
            .and()
            .authenticationProvider(authenticationProvider)
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable()
    }

    @Bean
    fun forbiddenEntryPoint(): AuthenticationEntryPoint = HttpStatusEntryPoint(HttpStatus.FORBIDDEN)
}

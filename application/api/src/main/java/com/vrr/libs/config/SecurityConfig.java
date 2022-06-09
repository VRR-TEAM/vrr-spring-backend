package com.vrr.libs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors().and().build();
//                .and()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                    .csrf().disable()
//                    .formLogin().disable()
//                    .httpBasic().disable()
//                    .exceptionHandling()
//                    .authenticationEntryPoint()
//                    .accessDeniedHandler()
//                .and()
//                    .authorizeRequests()
//                    .anyRequest().authenticated().
//                .build();
    }
}

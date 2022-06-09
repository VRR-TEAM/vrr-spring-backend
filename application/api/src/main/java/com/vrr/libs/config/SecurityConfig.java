package com.vrr.libs.config;

import com.vrr.libs.auth.filter.TokenAuthenticationFilter;
import com.vrr.libs.auth.filter.RestAuthenticationEntryPoint;
import com.vrr.libs.auth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthTokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                    .authorizeRequests()
                    // Add API End-Point to Allow Access
                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }
}

package com.dsm.nms.global.security;

import com.dsm.nms.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .formLogin().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/email/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/email/**").permitAll()
                .antMatchers(HttpMethod.POST, "/teacher").permitAll()
                .antMatchers(HttpMethod.POST, "/teacher/auth").permitAll()
                .antMatchers(HttpMethod.PUT, "/teacher/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/teacher/password").permitAll()
                .antMatchers(HttpMethod.PUT, "/teacher/password").permitAll()
                .antMatchers(HttpMethod.POST, "/student").permitAll()
                .antMatchers(HttpMethod.POST, "/student/auth").permitAll()
                .antMatchers(HttpMethod.PUT, "/student/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/student/password").permitAll()
                .antMatchers(HttpMethod.PUT, "/student/password").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider));
    }

}

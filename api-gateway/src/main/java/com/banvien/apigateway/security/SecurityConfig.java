package com.banvien.apigateway.security;

import com.banvien.apigateway.filter.CustomAuthorizationFilter;
import com.banvien.apigateway.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
                .and()
                .authorizeRequests().antMatchers(jwtConfig.getUri()).permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/customers/**", "/api/v1/products/**", "/api/v1/orders/**")
                .hasAnyAuthority("ADMIN_ROLE", "USER_ROLE")
                .antMatchers("/api/v1/customers/**", "/api/v1/products/**", "/api/v1/orders/**").hasRole("ADMIN_ROLE")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new CustomAuthorizationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}


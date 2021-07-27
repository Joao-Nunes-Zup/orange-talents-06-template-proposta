package com.ot6.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests(request ->
                request
                    .antMatchers(HttpMethod.GET, "/actuator/prometheus").hasRole("admin")
                    .antMatchers(HttpMethod.GET, "/proposals/**").hasAuthority("SCOPE_proposals:read")
                    .antMatchers(HttpMethod.POST, "/proposals").hasAuthority("SCOPE_proposals:write")
                    .antMatchers(HttpMethod.GET, "/biometrics/**").hasAuthority("SCOPE_biometrics:read")
                    .antMatchers(HttpMethod.POST, "/biometrics").hasAuthority("SCOPE_biometrics:write")
                    .antMatchers(HttpMethod.GET, "/blockages/**").hasAuthority("SCOPE_blockages:read")
                    .antMatchers(HttpMethod.POST, "/blockages").hasAuthority("SCOPE_blockages:write")
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}

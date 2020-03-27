package com.orange.orange_vote.base.security;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] AUTHORIZE_REQUESTS = {"/images/**", "/styles/**", "/scripts/**", "/api/**"};

    @Autowired
    @Qualifier(value = "customUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(AUTHORIZE_REQUESTS).permitAll()
            // .anyRequest().authenticated()
            .and().addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).and().formLogin()
            .loginPage("/login").and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessfully")
            .invalidateHttpSession(true).clearAuthentication(true).permitAll();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter customAuthenticationFilter() {
        UsernamePasswordAuthenticationFilter authFilter = new UsernamePasswordAuthenticationFilter();
        List<AuthenticationProvider> authenticationProviderList = Lists.newArrayList(authenticationProvider());
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProviderList);
        authFilter.setAuthenticationManager(authenticationManager);
        authFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler());
        return authFilter;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO: will replace by BCrypt encoder
        // return new BCryptPasswordEncoder();
        return new PlainTextPasswordEncoder();
    }

    public class PlainTextPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return charSequence.toString().equals(s);
        }

    }

}

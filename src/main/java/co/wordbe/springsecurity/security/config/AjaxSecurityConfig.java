package co.wordbe.springsecurity.security.config;

import co.wordbe.springsecurity.security.filter.AjaxLoginProcessingFilter;
import co.wordbe.springsecurity.security.provider.AjaxAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@Order(99)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AjaxLoginProcessingFilter ajaxLoginProcessingFilter;
    private final AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated()
        .and()
                .addFilterBefore(ajaxLoginProcessingFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        http.csrf().disable();

    }
}

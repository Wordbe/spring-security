package co.wordbe.springsecurity.security.config;

import co.wordbe.springsecurity.security.filter.AjaxLoginProcessingFilter;
import co.wordbe.springsecurity.security.handler.AjaxAuthenticationFailureHandler;
import co.wordbe.springsecurity.security.handler.AjaxAuthenticationSuccessHandler;
import co.wordbe.springsecurity.security.handler.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Order(98)
@Configuration
public class SecurityComponent extends WebSecurityConfigurerAdapter {

    private final AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
    private final AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        CustomAccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler();
        customAccessDeniedHandler.setErrorPage("/denied");
        return customAccessDeniedHandler;
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler);
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler);
        return ajaxLoginProcessingFilter;
    }
}

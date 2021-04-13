package co.wordbe.springsecurity.security.config;

import co.wordbe.springsecurity.security.factory.MethodResourcesFactoryBean;
import co.wordbe.springsecurity.security.processor.ProtectPointcutPostProcessor;
import co.wordbe.springsecurity.service.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final SecurityResourceService securityResourceService;

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return mapBasedMethodSecurityMetadataSource();
    }

    @Bean
    public MapBasedMethodSecurityMetadataSource mapBasedMethodSecurityMetadataSource() {
        return new MapBasedMethodSecurityMetadataSource(methodResourcesFactoryBean().getObject());
    }

    @Bean
    public MethodResourcesFactoryBean methodResourcesFactoryBean() {
        return new MethodResourcesFactoryBean(securityResourceService, "method");
    }

    @Bean
    public ProtectPointcutPostProcessor protectPointcutPostProcessor() {
        ProtectPointcutPostProcessor protectPointcutPostProcessor = new ProtectPointcutPostProcessor(mapBasedMethodSecurityMetadataSource());
        System.out.println(pointMethodResourcesFactoryBean().getObject());
        protectPointcutPostProcessor.setPointcutMap(pointMethodResourcesFactoryBean().getObject());
        return protectPointcutPostProcessor;
    }

    @Bean
    public MethodResourcesFactoryBean pointMethodResourcesFactoryBean() {
        return new MethodResourcesFactoryBean(securityResourceService, "pointcut");
    }
}

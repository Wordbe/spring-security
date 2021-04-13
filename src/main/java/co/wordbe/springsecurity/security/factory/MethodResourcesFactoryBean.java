package co.wordbe.springsecurity.security.factory;

import co.wordbe.springsecurity.service.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class MethodResourcesFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

    private  SecurityResourceService securityResourceService;
    private String resourceType;

    public MethodResourcesFactoryBean(SecurityResourceService securityResourceService, String resourceType) {
        this.securityResourceService = securityResourceService;
        this.resourceType = resourceType;
    }

    private LinkedHashMap<String, List<ConfigAttribute>> resourceMap;

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getObject() {
        if (resourceMap == null) {
            init();
        }

        return resourceMap;
    }

    private void init() {
        if ("method".equals(resourceType)) {
            resourceMap = securityResourceService.getMethodResourceList();
        } else if ("pointcut".equals(resourceType)) {
            resourceMap = securityResourceService.getPointcutResourceList();
        } else {
            log.error("resourceType must be 'method' or 'pointcut'");
        }
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

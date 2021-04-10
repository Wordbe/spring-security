//package co.wordbe.springsecurity.security.factory;
//
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//
//public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
//
//    private SecurityResourceService securityResourceService;
//    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;
//
//    public void setResourceMap(SecurityResourceService securityResourceService) {
//        this.resourceMap = resourceMap;
//    }
//
//    @Override
//    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
//
//        if (resourceMap == null) {
//            init();
//        }
//        return null;
//    }
//
//    private void init() {
//        resourceMap = securityResourceService.getResourceList();
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return LinkedHashMap.class;
//    }
//
//    @Override
//    public boolean isSingleton() {
//        return false;
//    }
//}

package co.wordbe.springsecurity.service;

import co.wordbe.springsecurity.domain.entity.AccessIp;
import co.wordbe.springsecurity.domain.entity.Resources;
import co.wordbe.springsecurity.repository.AccessIpRepository;
import co.wordbe.springsecurity.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SecurityResourceService {

    private final ResourcesRepository resourcesRepository;
    private final AccessIpRepository accessIpRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        List<Resources> resourcesList = resourcesRepository.findAllResources();

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        // 모든 자원(resource)마다
        resourcesList.forEach(re -> {
            List<ConfigAttribute> configAttributesList = new ArrayList<>();

            // 자원에 1:N 으로 매칭된 role 마다
            re.getRoleSet().forEach(role -> {
                configAttributesList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(new AntPathRequestMatcher(re.getResourceName()), configAttributesList);
        });
        return result;
    }

    public List<String> getAccessIpList() {
        List<String> accessIpList = accessIpRepository.findAll().stream()
                .map(AccessIp::getIpAddress)
                .collect(Collectors.toList());
        return accessIpList;
    }

    public LinkedHashMap<String, List<ConfigAttribute>> getMethodResourceList() {
        List<Resources> resourcesList = resourcesRepository.findAllMethodResources();

        LinkedHashMap<String, List<ConfigAttribute>> result = new LinkedHashMap<>();
        // 모든 자원(resource)마다
        resourcesList.forEach(re -> {
            List<ConfigAttribute> configAttributesList = new ArrayList<>();

            // 자원에 1:N 으로 매칭된 role 마다
            re.getRoleSet().forEach(role -> {
                configAttributesList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(re.getResourceName(), configAttributesList);
        });
        return result;
    }
}

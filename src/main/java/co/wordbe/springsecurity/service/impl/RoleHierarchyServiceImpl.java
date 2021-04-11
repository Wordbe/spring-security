package co.wordbe.springsecurity.service.impl;

import co.wordbe.springsecurity.domain.entity.RoleHierarchy;
import co.wordbe.springsecurity.repository.RoleHierarchyRepository;
import co.wordbe.springsecurity.service.RoleHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    private final RoleHierarchyRepository roleHierarchyRepository;

    @Transactional
    @Override
    public String findAllHierarchy() {
        List<RoleHierarchy> roleHierarchies = roleHierarchyRepository.findAll();

        Iterator<RoleHierarchy> iterator = roleHierarchies.iterator();
        StringBuilder concatenatedRoles = new StringBuilder();
        while(iterator.hasNext()) {
            RoleHierarchy roleHierarchy = iterator.next();
            if (roleHierarchy.getParentName() != null) {
                concatenatedRoles.append(roleHierarchy.getParentName().getChildName())
                        .append(" > ")
                        .append(roleHierarchy.getChildName())
                        .append("\n");
            }
        }
        return concatenatedRoles.toString();
    }
}

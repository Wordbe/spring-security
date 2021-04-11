package co.wordbe.springsecurity.service.impl;

import co.wordbe.springsecurity.domain.entity.Role;
import co.wordbe.springsecurity.repository.RoleRepository;
import co.wordbe.springsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    @Transactional
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}

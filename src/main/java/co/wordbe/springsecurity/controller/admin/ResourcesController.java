package co.wordbe.springsecurity.controller.admin;

import co.wordbe.springsecurity.domain.dto.ResourcesDto;
import co.wordbe.springsecurity.domain.entity.Resources;
import co.wordbe.springsecurity.domain.entity.Role;
import co.wordbe.springsecurity.repository.RoleRepository;
import co.wordbe.springsecurity.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import co.wordbe.springsecurity.service.ResourcesService;
import co.wordbe.springsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class ResourcesController {

    private final ResourcesService resourcesService;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
//    private final MethodSecurityService methodSecurityService;

    @GetMapping(value="/admin/resources")
    public String getResources(Model model) {
        List<Resources> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    @PostMapping(value="/admin/resources")
    public String createResources(ResourcesDto resourcesDto) {
        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        ModelMapper modelMapper = new ModelMapper();
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);
        urlFilterInvocationSecurityMetadataSource.reload();
//        methodSecurityService.addMethodSecured(resourcesDto.getResourceName(),resourcesDto.getRoleName());

        return "redirect:/admin/resources";
    }

    @GetMapping(value="/admin/resources/register")
    public String viewRoles(Model model) {
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);

        ResourcesDto resources = new ResourcesDto();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role());
        resources.setRoleSet(roleSet);
        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/{id}")
    public String getResources(@PathVariable String id, Model model) {
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);

        Resources resources = resourcesService.getResources(Long.valueOf(id));
        ModelMapper modelMapper = new ModelMapper();
        ResourcesDto resourcesDto = modelMapper.map(resources, ResourcesDto.class);
        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) {
        resourcesService.deleteResources(Long.valueOf(id));
        urlFilterInvocationSecurityMetadataSource.reload();

//        Resources resources = resourcesService.getResources(Long.valueOf(id));
//        methodSecurityService.removeMethodSecured(resources.getResourceName());

        return "redirect:/admin/resources";
    }
}

package co.wordbe.springsecurity.domain.dto;

import co.wordbe.springsecurity.domain.entity.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ResourcesDto {

    private String id;
    private String resourceName;
    private String httpMethod;
    private Integer orderNum;
    private String resourceType;
    private String roleName;
    private Set<Role> roleSet;
}

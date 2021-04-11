package co.wordbe.springsecurity.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor @Builder
@ToString(exclude = {"parentName", "roleHierarchy"})
@EqualsAndHashCode(of = "id")
@Entity @Table(name = "ROLE_HIERARCHY")
public class RoleHierarchy implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "child_name")
    private String childName;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name",
                referencedColumnName = "child_name")
    private RoleHierarchy parentName;

    @OneToMany(mappedBy = "parentName", cascade = {CascadeType.ALL})
    private Set<RoleHierarchy> roleHierarchy = new HashSet<>();
}

/**
 id,child_name,parent_name
 1,ROLE_ADMIN,
 2,ROLE_MANAGER,ROLE_ADMIN
 3,ROLE_USER,ROLE_MANAGER
 */
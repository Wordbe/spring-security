package co.wordbe.springsecurity.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//@EntityListeners(value = {AuditingEntityListener.class})
@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor @Builder
@ToString(exclude = {"roleSet"})
@EqualsAndHashCode(of = "id")
@Entity @Table(name = "RESOURCES")
public class Resources implements Serializable {

    @Id @GeneratedValue
    @Column(name = "resource_id")
    private Long id;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "order_num")
    private int orderNum;

    @Column(name = "resource_type")
    private String resourceType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_resources",
            joinColumns = {@JoinColumn(name = "resource_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet = new HashSet<>();

}

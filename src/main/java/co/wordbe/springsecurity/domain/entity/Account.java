package co.wordbe.springsecurity.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor @Builder
@ToString(exclude = {"userRoles"})
@EqualsAndHashCode(of = "id")
@Entity
public class Account implements Serializable {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "account_roles",
            joinColumns = { @JoinColumn(name = "account_id")},
            inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private Set<Role> userRoles = new HashSet<>();
}

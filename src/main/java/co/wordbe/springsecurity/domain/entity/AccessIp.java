package co.wordbe.springsecurity.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor @Builder
@ToString(exclude = {"userRoles"})
@EqualsAndHashCode(of = "id")
@Entity @Table(name = "ACCESS_IP")
public class AccessIp implements Serializable {

    @Id @GeneratedValue
    @Column(name = "IP_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;
}

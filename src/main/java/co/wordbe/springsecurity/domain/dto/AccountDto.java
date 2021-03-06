package co.wordbe.springsecurity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class AccountDto {

    private String id;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private List<String> roles;
}

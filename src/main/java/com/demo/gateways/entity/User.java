package com.demo.gateways.entity;

import com.demo.gateways.model.UserDto;
import com.demo.gateways.util.ListUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "Please provide a valid Email")
    @NotEmpty(message = "*Please provide an Email")
    String email;

    @Length(min = 3, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @JsonIgnore
    String password;

    int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;

    public UserDto asModel() {
        return UserDto.builder()
                .id(this.id)
                .email(this.email)
                .active(this.active)
                .roles(ListUtil.copy(this.roles, Role::asModel))
                .build();
    }
}
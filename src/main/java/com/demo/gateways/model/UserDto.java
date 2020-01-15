package com.demo.gateways.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String email;
    String password;
    int active;
    List<RoleDto> roles;
}

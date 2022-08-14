package com.example.coffeerest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
}

package com.example.coffeerest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAuth {
    private String username;
    private String password;
}

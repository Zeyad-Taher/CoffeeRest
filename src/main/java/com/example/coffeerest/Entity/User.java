package com.example.coffeerest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "String")
    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private CartItem cart;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username=username;
        this.email = email;
        this.password = password;
    }
}

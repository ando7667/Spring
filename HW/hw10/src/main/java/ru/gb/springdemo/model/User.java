package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Идентификатор пользователя")
    private long id;

    @Column(name = "login")
    @Schema(name = "логин пользователя")
    private String login;

    @Column(name = "password")
    @Schema(name = "логин пользователя")
    private String password;

    @Column(name = "role")
    @Schema(name = "роль пользователя")
//    private List<Role> role;
    private String role;

    public User() {
    }

}

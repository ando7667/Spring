package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(name = "Идентификатор роли пользователя")
    private long id;

    @Column(name = "role")
    @Schema(name = "роль пользователя")
    String role;
}

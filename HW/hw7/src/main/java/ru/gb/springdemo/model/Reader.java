package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "readers")
public class Reader {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "Идентификатор читателя")
  private long id;

  @Column(name = "name")
  @Schema(name = "Имя читателя")
  private String name;

  public Reader() {
  }
  public Reader(String name) {
    this.name = name;
  }

}

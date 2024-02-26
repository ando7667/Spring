package org.ignatov;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "readers")
public class Reader {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "Идентификатор читателя")
  private long id;

  @Column(name = "name")
  @Schema(name = "Имя читателя")
  private String name;


  public Reader(String name) {
    this.name = name;
  }

}

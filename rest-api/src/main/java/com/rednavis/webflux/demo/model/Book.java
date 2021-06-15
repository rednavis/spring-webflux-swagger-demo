package com.rednavis.webflux.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

  private String id;

  @NotNull
  @NotBlank
  private String name;

  // see https://en.wikipedia.org/wiki/International_Standard_Book_Number for details
  // TODO add validation rules according to standard definition
  @NotNull
  private String isbn;
}
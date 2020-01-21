package com.rednavis.webflux.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

@Data
@Builder
@With
@Value
public class Book {

  String id;

  @NotNull
  @NotBlank
  String name;

  // see https://en.wikipedia.org/wiki/International_Standard_Book_Number for details
  // TODO add validation rules according to standard definition
  @NotNull
  String isbn;
}
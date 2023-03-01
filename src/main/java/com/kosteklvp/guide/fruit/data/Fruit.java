package com.kosteklvp.guide.fruit.data;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Fruit {

  @NonNull
  @NotBlank
  private final String name;

  @Nullable
  private String description;

  public Fruit(@NonNull String name, @Nullable String description) {
    this.name = name;
    this.description = description;
  }

  @NonNull
  public String getName() {
    return name;
  }

  @Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

}

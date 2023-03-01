package com.kosteklvp.guide.fruit.data;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class FruitCommand {

  @NonNull
  @NotBlank
  private final String name;

  @Nullable
  private final String description;

  public FruitCommand(@NonNull String name) {
    this(name, null);
  }

  @Creator
  public FruitCommand(@NonNull String name, @Nullable String description) {
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

}

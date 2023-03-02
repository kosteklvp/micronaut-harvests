package com.kosteklvp.guide.harvest.data;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public abstract class HarvestCommand {

  @NonNull
  @NotBlank
  private final String name;

  @Nullable
  private final String description;

  protected HarvestCommand(@NonNull String name, @Nullable String description) {
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

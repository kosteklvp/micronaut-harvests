package com.kosteklvp.guide.vegetable.data;

import com.kosteklvp.guide.harvest.data.HarvestCommand;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class VegetableCommand extends HarvestCommand {

  public VegetableCommand(@NonNull String name) {
    this(name, null);
  }

  @Creator
  public VegetableCommand(@NonNull String name, @Nullable String description) {
    super(name, description);
  }

}

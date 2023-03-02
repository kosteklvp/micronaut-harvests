package com.kosteklvp.guide.fruit.data;

import com.kosteklvp.guide.harvest.data.HarvestCommand;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class FruitCommand extends HarvestCommand {

  public FruitCommand(@NonNull String name) {
    this(name, null);
  }

  @Creator
  public FruitCommand(@NonNull String name, @Nullable String description) {
    super(name, description);
  }

}

package com.kosteklvp.guide.fruit.data;

import com.kosteklvp.guide.harvest.data.Harvest;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

public class Fruit extends Harvest {

  public Fruit(@NonNull String name, @Nullable String description) {
    super(name, description);
  }

}

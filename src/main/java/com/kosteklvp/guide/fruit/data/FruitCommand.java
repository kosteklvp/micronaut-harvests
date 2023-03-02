package com.kosteklvp.guide.fruit.data;

import com.kosteklvp.guide.harvest.data.HarvestCommand;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class FruitCommand extends HarvestCommand {

  public FruitCommand(String name) {
    this(name, null);
  }

  @Creator
  public FruitCommand(String name, String description) {
    super(name, description);
  }

}

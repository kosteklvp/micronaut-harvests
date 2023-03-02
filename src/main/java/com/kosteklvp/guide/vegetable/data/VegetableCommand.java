package com.kosteklvp.guide.vegetable.data;

import com.kosteklvp.guide.harvest.data.HarvestCommand;

import io.micronaut.core.annotation.Creator;

public class VegetableCommand extends HarvestCommand {

  public VegetableCommand(String name) {
    this(name, null);
  }

  @Creator
  public VegetableCommand(String name, String description) {
    super(name, description);
  }

}

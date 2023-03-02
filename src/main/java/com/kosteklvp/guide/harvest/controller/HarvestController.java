package com.kosteklvp.guide.harvest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kosteklvp.guide.fruit.repository.IFruitRepository;
import com.kosteklvp.guide.harvest.data.Harvest;
import com.kosteklvp.guide.vegetable.repository.IVegetableRepository;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/harvests")
public class HarvestController {

  private final IFruitRepository fruitRepository;
  private final IVegetableRepository vegetableRepository;

  HarvestController(IFruitRepository fruitRepository, IVegetableRepository vegetableRepository) {
    this.fruitRepository = fruitRepository;
    this.vegetableRepository = vegetableRepository;
  }

  @Get
  Collection<Harvest> list() {
    List<Harvest> harvests = new ArrayList<>();
    harvests.addAll(fruitRepository.list());
    harvests.addAll(vegetableRepository.list());

    return harvests;
  }

}

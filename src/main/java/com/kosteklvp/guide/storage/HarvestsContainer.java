package com.kosteklvp.guide.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.vegetable.data.Vegetable;

import io.micronaut.core.annotation.NonNull;

public class HarvestsContainer {

  @NonNull
  protected final Map<String, Fruit> fruits = new ConcurrentHashMap<>();

  @NonNull
  protected final Map<String, Vegetable> vegetables = new ConcurrentHashMap<>();

  public Map<String, Fruit> getFruits() {
    return fruits;
  }

  public Map<String, Vegetable> getVegetables() {
    return vegetables;
  }

}

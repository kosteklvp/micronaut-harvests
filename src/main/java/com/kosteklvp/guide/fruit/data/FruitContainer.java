package com.kosteklvp.guide.fruit.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.micronaut.core.annotation.NonNull;

public class FruitContainer {

  @NonNull
  private final Map<String, Fruit> fruits = new ConcurrentHashMap<>();

  @NonNull
  public Map<String, Fruit> getFruits() {
    return fruits;
  }

}

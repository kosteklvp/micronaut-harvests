package com.kosteklvp.guide.harvest.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.micronaut.core.annotation.NonNull;

public abstract class HarvestContainer<H extends Harvest> {

  @NonNull
  protected final Map<String, H> harvests = new ConcurrentHashMap<>();

  public Map<String, H> getHarvests() {
    return harvests;
  }

}

//public class FruitContainer {
//
//  @NonNull
//  private final Map<String, Fruit> fruits = new ConcurrentHashMap<>();
//
//  @NonNull
//  public Map<String, Fruit> getFruits() {
//    return fruits;
//  }
//
//}

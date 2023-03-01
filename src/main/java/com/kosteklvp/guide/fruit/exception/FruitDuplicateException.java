package com.kosteklvp.guide.fruit.exception;

public class FruitDuplicateException extends RuntimeException {

  public FruitDuplicateException(String name) {
    super("Fruit '" + name + "' already exists.");
  }

}

package com.kosteklvp.guide.fruit.repository;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.fruit.data.FruitCommand;
import com.kosteklvp.guide.fruit.data.FruitContainer;
import com.kosteklvp.guide.fruit.exception.FruitDuplicateException;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.microstream.RootProvider;
import io.micronaut.microstream.annotations.StoreParams;
import io.micronaut.microstream.annotations.StoreReturn;
import jakarta.inject.Singleton;

@Singleton
public class FruitRepository implements IFruitRepository {

  private final RootProvider<FruitContainer> rootProvider;

  FruitRepository(RootProvider<FruitContainer> rootProvider) {
    this.rootProvider = rootProvider;
  }

  @Override
  @NonNull
  public Collection<Fruit> list() {
    return rootProvider.root().getFruits().values();
  }

  @Override
  @NonNull
  public Fruit create(@NonNull @NotNull @Valid FruitCommand fruit) throws FruitDuplicateException {
    Map<String, Fruit> fruits = rootProvider.root().getFruits();
    if (fruits.containsKey(fruit.getName())) {
      throw new FruitDuplicateException(fruit.getName());
    }

    return performCreate(fruits, fruit);
  }

  @StoreParams("fruits")
  protected Fruit performCreate(Map<String, Fruit> fruits, FruitCommand fruit) {
    Fruit newFruit = new Fruit(fruit.getName(), fruit.getDescription());
    fruits.put(fruit.getName(), newFruit);

    return newFruit;
  }

  @Nullable
  public Fruit update(@NonNull @NotNull @Valid FruitCommand fruit) {
    Map<String, Fruit> fruits = rootProvider.root().getFruits();
    Fruit foundFruit = fruits.get(fruit.getName());

    if (foundFruit != null) {
      return performUpdate(foundFruit, fruit);
    }

    return null;
  }

  @StoreReturn
  protected Fruit performUpdate(@NonNull Fruit foundFruit, @NonNull FruitCommand fruit) {
    foundFruit.setDescription(fruit.getDescription());
    return foundFruit;
  }

  @Override
  @Nullable
  public Fruit find(@NonNull @NotBlank String name) {
    return rootProvider.root().getFruits().get(name);
  }

  @Override
  public void delete(@NonNull @NotNull @Valid FruitCommand fruit) {
    performDelete(fruit);
  }

  @StoreReturn
  protected Map<String, Fruit> performDelete(FruitCommand fruit) {
    if (rootProvider.root().getFruits().remove(fruit.getName()) != null) {
      return rootProvider.root().getFruits();
    }

    return null;
  }

}

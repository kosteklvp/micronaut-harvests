package com.kosteklvp.guide.fruit.repository;

import static java.util.Collections.emptyMap;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.exception.DuplicateException;
import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.fruit.data.FruitCommand;
import com.kosteklvp.guide.storage.HarvestsContainer;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.microstream.RootProvider;
import io.micronaut.microstream.annotations.StoreParams;
import io.micronaut.microstream.annotations.StoreReturn;
import jakarta.inject.Singleton;

@Singleton
public class FruitRepository implements IFruitRepository {

  private final RootProvider<HarvestsContainer> rootProvider;

  FruitRepository(RootProvider<HarvestsContainer> rootProvider) {
    this.rootProvider = rootProvider;
  }

  @Override
  @NonNull
  public Collection<Fruit> list() {
    return rootProvider.root().getFruits().values();
  }

  @Override
  @NonNull
  public Fruit create(@NonNull @NotNull @Valid FruitCommand fruitCommand) throws DuplicateException {
    Map<String, Fruit> fruits = rootProvider.root().getFruits();
    if (fruits.containsKey(fruitCommand.getName())) {
      throw new DuplicateException(fruitCommand.getName());
    }

    return performCreate(fruits, fruitCommand);
  }

  @StoreParams("fruits")
  protected Fruit performCreate(Map<String, Fruit> fruits, FruitCommand fruitCommand) {
    Fruit newFruit = new Fruit(fruitCommand.getName(), fruitCommand.getDescription());
    fruits.put(fruitCommand.getName(), newFruit);

    return newFruit;
  }

  @Nullable
  public Fruit update(@NonNull @NotNull @Valid FruitCommand fruitCommand) {
    Map<String, Fruit> fruits = rootProvider.root().getFruits();
    Fruit foundFruit = fruits.get(fruitCommand.getName());

    if (foundFruit != null) {
      return performUpdate(foundFruit, fruitCommand);
    }

    return null;
  }

  @StoreReturn
  protected Fruit performUpdate(@NonNull Fruit foundFruit, @NonNull FruitCommand fruitCommand) {
    foundFruit.setDescription(fruitCommand.getDescription());
    return foundFruit;
  }

  @Override
  @Nullable
  public Fruit find(@NonNull @NotBlank String name) {
    return rootProvider.root().getFruits().get(name);
  }

  @Override
  public void delete(@NonNull @NotNull @Valid FruitCommand fruitCommand) {
    performDelete(fruitCommand);
  }

  @StoreReturn
  protected Map<String, Fruit> performDelete(FruitCommand fruitCommand) {
    if (rootProvider.root().getFruits().remove(fruitCommand.getName()) != null) {
      return rootProvider.root().getFruits();
    }

    return emptyMap();
  }

}

package com.kosteklvp.guide.vegetable.repository;

import static java.util.Collections.emptyMap;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.exception.DuplicateException;
import com.kosteklvp.guide.storage.HarvestsContainer;
import com.kosteklvp.guide.vegetable.data.Vegetable;
import com.kosteklvp.guide.vegetable.data.VegetableCommand;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.microstream.RootProvider;
import io.micronaut.microstream.annotations.StoreParams;
import io.micronaut.microstream.annotations.StoreReturn;
import jakarta.inject.Singleton;

@Singleton
public class VegetableRepository implements IVegetableRepository {

  private final RootProvider<HarvestsContainer> rootProvider;

  VegetableRepository(RootProvider<HarvestsContainer> rootProvider) {
    this.rootProvider = rootProvider;
  }

  @Override
  @NonNull
  public Collection<Vegetable> list() {
    return rootProvider.root().getVegetables().values();
  }

  @Override
  @NonNull
  public Vegetable create(@NonNull @NotNull @Valid VegetableCommand vegetableCommand) throws DuplicateException {
    Map<String, Vegetable> vegetables = rootProvider.root().getVegetables();
    if (vegetables.containsKey(vegetableCommand.getName())) {
      throw new DuplicateException(vegetableCommand.getName());
    }

    return performCreate(vegetables, vegetableCommand);
  }

  @StoreParams("vegetables")
  protected Vegetable performCreate(Map<String, Vegetable> vegetables, VegetableCommand vegetableCommand) {
    Vegetable newVegetable = new Vegetable(vegetableCommand.getName(), vegetableCommand.getDescription());
    vegetables.put(vegetableCommand.getName(), newVegetable);

    return newVegetable;
  }

  @Nullable
  public Vegetable update(@NonNull @NotNull @Valid VegetableCommand vegetableCommand) {
    Map<String, Vegetable> vegetables = rootProvider.root().getVegetables();
    Vegetable foundVegetable = vegetables.get(vegetableCommand.getName());

    if (foundVegetable != null) {
      return performUpdate(foundVegetable, vegetableCommand);
    }

    return null;
  }

  @StoreReturn
  protected Vegetable performUpdate(@NonNull Vegetable foundVegetable, @NonNull VegetableCommand vegetableCommand) {
    foundVegetable.setDescription(vegetableCommand.getDescription());
    return foundVegetable;
  }

  @Override
  @Nullable
  public Vegetable find(@NonNull @NotBlank String name) {
    return rootProvider.root().getVegetables().get(name);
  }

  @Override
  public void delete(@NonNull @NotNull @Valid VegetableCommand vegetableCommand) {
    performDelete(vegetableCommand);
  }

  @StoreReturn
  protected Map<String, Vegetable> performDelete(VegetableCommand vegetableCommand) {
    if (rootProvider.root().getVegetables().remove(vegetableCommand.getName()) != null) {
      return rootProvider.root().getVegetables();
    }

    return emptyMap();
  }

}

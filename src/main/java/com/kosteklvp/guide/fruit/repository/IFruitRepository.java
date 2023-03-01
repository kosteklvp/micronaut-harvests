package com.kosteklvp.guide.fruit.repository;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.fruit.data.FruitCommand;
import com.kosteklvp.guide.fruit.exception.FruitDuplicateException;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

public interface IFruitRepository {

  @NonNull
  Collection<Fruit> list();

  @NonNull
  Fruit create(@NonNull @NotNull @Valid FruitCommand fruit) throws FruitDuplicateException;

  @Nullable
  Fruit update(@NonNull @NotNull @Valid FruitCommand fruit);

  @Nullable
  Fruit find(@NonNull @NotBlank String name);

  void delete(@NonNull @NotNull @Valid FruitCommand fruit);

}

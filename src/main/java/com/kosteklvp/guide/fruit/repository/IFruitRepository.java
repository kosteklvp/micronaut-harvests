package com.kosteklvp.guide.fruit.repository;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.exception.DuplicateException;
import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.fruit.data.FruitCommand;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

public interface IFruitRepository {

  @NonNull
  Collection<Fruit> list();

  @NonNull
  Fruit create(@NonNull @NotNull @Valid FruitCommand command) throws DuplicateException;

  @Nullable
  Fruit update(@NonNull @NotNull @Valid FruitCommand command);

  @Nullable
  Fruit find(@NonNull @NotBlank String name);

  void delete(@NonNull @NotNull @Valid FruitCommand command);

}

package com.kosteklvp.guide.vegetable.repository;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.exception.DuplicateException;
import com.kosteklvp.guide.vegetable.data.Vegetable;
import com.kosteklvp.guide.vegetable.data.VegetableCommand;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

public interface IVegetableRepository {

  @NonNull
  Collection<Vegetable> list();

  @NonNull
  Vegetable create(@NonNull @NotNull @Valid VegetableCommand command) throws DuplicateException;

  @Nullable
  Vegetable update(@NonNull @NotNull @Valid VegetableCommand command);

  @Nullable
  Vegetable find(@NonNull @NotBlank String name);

  void delete(@NonNull @NotNull @Valid VegetableCommand command);

}

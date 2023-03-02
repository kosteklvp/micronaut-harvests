package com.kosteklvp.guide.fruit.repository;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.exception.DuplicateException;
import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.harvest.data.Harvest;
import com.kosteklvp.guide.harvest.data.HarvestCommand;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

public interface IFruitRepository<H extends Harvest, HC extends HarvestCommand> {

  @NonNull
  Collection<Fruit> list();

  @NonNull
  H create(@NonNull @NotNull @Valid HC fruit) throws DuplicateException;

  @Nullable
  H update(@NonNull @NotNull @Valid HC fruit);

  @Nullable
  H find(@NonNull @NotBlank String name);

  void delete(@NonNull @NotNull @Valid HC fruit);

}

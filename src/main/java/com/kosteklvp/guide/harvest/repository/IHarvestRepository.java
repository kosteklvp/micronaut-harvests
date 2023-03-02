package com.kosteklvp.guide.harvest.repository;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.exception.DuplicateException;
import com.kosteklvp.guide.harvest.data.Harvest;
import com.kosteklvp.guide.harvest.data.HarvestCommand;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

public interface IHarvestRepository<H extends Harvest, C extends HarvestCommand> {

  @NonNull
  Collection<H> list();

  @NonNull
  H create(@NonNull @NotNull @Valid C command) throws DuplicateException;

  @Nullable
  H update(@NonNull @NotNull @Valid C command);

  @Nullable
  H find(@NonNull @NotBlank String name);

  void delete(@NonNull @NotNull @Valid C command);

}

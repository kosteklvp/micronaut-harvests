package com.kosteklvp.guide.vegetable.controller;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.vegetable.data.Vegetable;
import com.kosteklvp.guide.vegetable.data.VegetableCommand;
import com.kosteklvp.guide.vegetable.repository.VegetableRepository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller("/vegetables")
public class VegetableController {

  private final VegetableRepository vegetableRepository;

  VegetableController(VegetableRepository vegetableRepository) {
    this.vegetableRepository = vegetableRepository;
  }

  @Get
  Collection<Vegetable> list() {
    return vegetableRepository.list();
  }

  @ExecuteOn(TaskExecutors.IO)
  @Post
  @Status(HttpStatus.CREATED)
  Vegetable create(@NonNull @NotNull @Valid @Body VegetableCommand vegetableCommand) {
    return vegetableRepository.create(vegetableCommand);
  }

  @Put
  Vegetable update(@NonNull @NotNull @Valid @Body VegetableCommand vegetableCommand) {
    return vegetableRepository.update(vegetableCommand);
  }

  @Get("/{name}")
  Vegetable find(@PathVariable String name) {
    return vegetableRepository.find(name);
  }

  @ExecuteOn(TaskExecutors.IO)
  @Delete
  @Status(HttpStatus.NO_CONTENT)
  void delete(@NonNull @Valid @Body VegetableCommand vegetableCommand) {
    vegetableRepository.delete(vegetableCommand);
  }

}

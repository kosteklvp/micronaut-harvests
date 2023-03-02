package com.kosteklvp.guide.fruit.controller;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.fruit.data.FruitCommand;
import com.kosteklvp.guide.fruit.repository.IFruitRepository;

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

@Controller("/fruits")
class FruitController {

  private final IFruitRepository fruitRepository;

  FruitController(IFruitRepository fruitRepository) {
    this.fruitRepository = fruitRepository;
  }

  @Get
  Collection<Fruit> list() {
    return fruitRepository.list();
  }

  @ExecuteOn(TaskExecutors.IO)
  @Post
  @Status(HttpStatus.CREATED)
  Fruit create(@NonNull @NotNull @Valid @Body FruitCommand fruit) {
    return fruitRepository.create(fruit);
  }

  @Put
  Fruit update(@NonNull @NotNull @Valid @Body FruitCommand fruit) {
    return fruitRepository.update(fruit);
  }

  @Get("/{name}")
  Fruit find(@PathVariable String name) {
    return fruitRepository.find(name);
  }

  @ExecuteOn(TaskExecutors.IO)
  @Delete
  @Status(HttpStatus.NO_CONTENT)
  void delete(@NonNull @Valid @Body FruitCommand fruit) {
    fruitRepository.delete(fruit);
  }

}

package com.kosteklvp.guide.fruit;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.fruit.data.FruitCommand;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;

@Client("/fruits")
public interface FruitClient {

  @Get
  Iterable<Fruit> list();

  @Get("/{name}")
  Optional<Fruit> find(@NonNull @NotBlank @PathVariable String name);

  @Post
  HttpResponse<Fruit> create(@NonNull @NotNull @Valid @Body FruitCommand fruit);

  @Put
  Optional<Fruit> update(@NonNull @NotNull @Valid @Body FruitCommand fruit);

  @NonNull
  @Delete
  HttpStatus delete(@NonNull @Valid @Body FruitCommand fruit);

}

package com.kosteklvp.guide.fruit.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

import com.kosteklvp.guide.BaseTest;
import com.kosteklvp.guide.fruit.FruitClient;
import com.kosteklvp.guide.fruit.data.Fruit;
import com.kosteklvp.guide.fruit.data.FruitCommand;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.runtime.server.EmbeddedServer;

class FruitControllerTest extends BaseTest {

  @Test
  void testInteractionWithTheController() {
    FruitCommand apple = new FruitCommand("apple", "Keeps the doctor away");
    String bananaName = "banana";
    String bananaDescription = "Yellow and curved";
    Map<String, Object> properties = new HashMap<>(getProperties());

    try (EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer.class, properties)) {
      FruitClient fruitClient = embeddedServer.getApplicationContext().getBean(FruitClient.class);
      HttpResponse<Fruit> response = fruitClient.create(new FruitCommand(bananaName));
      assertEquals(HttpStatus.CREATED, response.getStatus());
      assertTrue(response.getBody().isPresent());

      Fruit banana = response.getBody().get();
      List<Fruit> fruitList = fruitsList(fruitClient);
      assertEquals(1, fruitList.size());
      assertEquals(banana.getName(), fruitList.get(0).getName());
      assertNull(fruitList.get(0).getDescription());

      Optional<Fruit> bananaOptional = fruitClient.update(apple);
      assertFalse(bananaOptional.isPresent());

      response = fruitClient.create(apple);
      assertEquals(HttpStatus.CREATED, response.getStatus());
      assertTrue(fruitsStream(fruitClient).anyMatch(f -> "Keeps the doctor away".equals(f.getDescription())));

      bananaOptional = fruitClient.update(new FruitCommand(bananaName, bananaDescription));
      assertTrue(bananaOptional.isPresent());
      assertEquals(
          Stream.of("Keeps the doctor away", "Yellow and curved").collect(Collectors.toSet()),
          fruitsStream(fruitClient)
              .map(Fruit::getDescription)
              .collect(toSet()));
    }

    try (EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer.class, properties)) {
      FruitClient fruitClient = embeddedServer.getApplicationContext().getBean(FruitClient.class);
      assertEquals(2, numberOfFruits(fruitClient));
      fruitClient.delete(apple);
      fruitClient.delete(new FruitCommand(bananaName, bananaDescription));
    }

    try (EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer.class, properties)) {
      FruitClient fruitClient = embeddedServer.getApplicationContext().getBean(FruitClient.class);
      assertEquals(0, numberOfFruits(fruitClient));
    }

  }

  private int numberOfFruits(FruitClient fruitClient) {
    return fruitsList(fruitClient).size();
  }

  private List<Fruit> fruitsList(FruitClient fruitClient) {
    return fruitsStream(fruitClient).collect(toList());
  }

  private Stream<Fruit> fruitsStream(FruitClient fruitClient) {
    Iterable<Fruit> fruits = fruitClient.list();

    return StreamSupport.stream(fruits.spliterator(), false);
  }

}

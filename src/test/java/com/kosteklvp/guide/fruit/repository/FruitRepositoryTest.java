package com.kosteklvp.guide.fruit.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.kosteklvp.guide.fruit.data.FruitCommand;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest(startApplication = false)
class FruitRepositoryTest {

  @Inject
  IFruitRepository fruitRepository;

  @Test
  void methodsValidateParameters() {
    Executable e = () -> fruitRepository.create(new FruitCommand(""));

    assertThrows(ConstraintViolationException.class, e);
  }

}

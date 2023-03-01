package com.kosteklvp.guide.fruit.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.kosteklvp.guide.BaseTest;
import com.kosteklvp.guide.fruit.data.FruitCommand;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FruitDuplicationExceptionHandlerTest extends BaseTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  void duplicatedFruitsReturns400() {
    FruitCommand banana = new FruitCommand("Banana");
    HttpRequest<?> request = HttpRequest.POST("/fruits", banana);
    HttpResponse<?> response = httpClient.toBlocking().exchange(request);
    assertEquals(HttpStatus.CREATED, response.status());

    HttpClientResponseException exception = assertThrows(HttpClientResponseException.class,
        () -> httpClient.toBlocking().exchange(request));
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getStatus());

    HttpRequest<?> deleteRequest = HttpRequest.DELETE("/fruits", banana);
    HttpResponse<?> deleteResponse = httpClient.toBlocking().exchange(deleteRequest);
    assertEquals(HttpStatus.NO_CONTENT, deleteResponse.status());
  }

}

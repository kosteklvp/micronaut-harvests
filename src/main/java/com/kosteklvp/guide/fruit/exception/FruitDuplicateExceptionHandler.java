package com.kosteklvp.guide.fruit.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;

public class FruitDuplicateExceptionHandler implements ExceptionHandler<FruitDuplicateException, HttpResponse<?>> {

  private final ErrorResponseProcessor<?> errorResponseProcessor;

  public FruitDuplicateExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
    this.errorResponseProcessor = errorResponseProcessor;
  }

  @Override
  public HttpResponse<?> handle(HttpRequest request, FruitDuplicateException exception) {
    ErrorContext errorContext = ErrorContext.builder(request)
        .cause(exception)
        .errorMessage(exception.getMessage())
        .build();

    return errorResponseProcessor.processResponse(errorContext, HttpResponse.unprocessableEntity());
  }

}

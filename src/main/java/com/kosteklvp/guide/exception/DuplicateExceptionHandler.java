package com.kosteklvp.guide.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;

public class DuplicateExceptionHandler implements ExceptionHandler<DuplicateException, HttpResponse<?>> {

  private final ErrorResponseProcessor<?> errorResponseProcessor;

  public DuplicateExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
    this.errorResponseProcessor = errorResponseProcessor;
  }

  @Override
  public HttpResponse<?> handle(HttpRequest request, DuplicateException exception) {
    ErrorContext errorContext = ErrorContext.builder(request)
        .cause(exception)
        .errorMessage(exception.getMessage())
        .build();

    return errorResponseProcessor.processResponse(errorContext, HttpResponse.unprocessableEntity());
  }

}

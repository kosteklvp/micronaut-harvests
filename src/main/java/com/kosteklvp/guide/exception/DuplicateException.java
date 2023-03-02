package com.kosteklvp.guide.exception;

public class DuplicateException extends RuntimeException {

  public DuplicateException(String name) {
    super("'" + name + "' already exists.");
  }

}

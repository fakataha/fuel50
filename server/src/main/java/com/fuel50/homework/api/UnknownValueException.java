package com.fuel50.homework.api;

public class UnknownValueException extends RuntimeException {
  public UnknownValueException() {}

  public UnknownValueException(String message) {
    super(message);
  }

  public UnknownValueException(String message, Throwable cause) {
    super(message, cause);
  }
}

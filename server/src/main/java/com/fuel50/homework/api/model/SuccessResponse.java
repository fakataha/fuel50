package com.fuel50.homework.api.model;

public final class SuccessResponse {

  private final int httpStatusCode;

  private final String message;

  private final Object body;

  private SuccessResponse(Builder builder) {
    this.httpStatusCode = builder.httpStatusCode;
    this.message = builder.message;
    this.body = builder.body;
  }

  public int getHttpStatusCode() {
    return httpStatusCode;
  }

  public String getMessage() {
    return message;
  }

  public Object getBody() {
    return body;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private int httpStatusCode;

    private String message;

    private Object body;

    private Builder() {}

    public Builder setHttpStatusCode(int httpStatusCode) {
      this.httpStatusCode = httpStatusCode;
      return this;
    }

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder setBody(Object body) {
      this.body = body;
      return this;
    }

    public SuccessResponse build() {
      return new SuccessResponse(this);
    }
  }
}

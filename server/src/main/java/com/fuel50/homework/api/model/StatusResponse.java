package com.fuel50.homework.api.model;

import java.time.OffsetDateTime;

public final class StatusResponse {

  private final OffsetDateTime lastRecordEvent;

  private final boolean up;

  private final int numberOfRecords;

  private StatusResponse(Builder builder) {
    this.lastRecordEvent = builder.lastRecordEvent;
    this.up = builder.up;
    this.numberOfRecords = builder.numberOfRecords;
  }

  public OffsetDateTime getLastRecordEvent() {
    return lastRecordEvent;
  }

  public boolean isUp() {
    return up;
  }

  public int getNumberOfRecords() {
    return numberOfRecords;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private OffsetDateTime lastRecordEvent;

    private boolean up;

    private int numberOfRecords;

    private Builder() {}

    public Builder setLastRecordEvent(OffsetDateTime lastRecordEvent) {
      this.lastRecordEvent = lastRecordEvent;
      return this;
    }

    public Builder setUp(boolean up) {
      this.up = up;
      return this;
    }

    public Builder setNumberOfRecords(int numberOfRecords) {
      this.numberOfRecords = numberOfRecords;
      return this;
    }

    public StatusResponse build() {
      return new StatusResponse(this);
    }
  }
}

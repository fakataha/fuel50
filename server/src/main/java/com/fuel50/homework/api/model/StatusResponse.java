package com.fuel50.homework.api.model;

import java.util.List;

public class StatusResponse {

  private boolean up;
  private List<String> examples;

  public StatusResponse(boolean up, List<String> examples) {
    this.up = up;
    this.examples = examples;
  }

  public boolean isUp() {
    return up;
  }

  public List<String> getExamples() {
    return examples;
  }
}

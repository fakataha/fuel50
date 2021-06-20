package com.fuel50.homework.api.model;

import java.util.ArrayList;
import java.util.List;

public final class SummaryResponse {

  private final int happy;

  private final int justNormalReally;

  private final int aBitMeh;

  private final int grumpy;

  private final int stressedOutNotAHappyCamper;

  private final List<String> moodMessages;

  private SummaryResponse(Builder builder) {
    this.happy = builder.happy;
    this.justNormalReally = builder.justNormalReally;
    this.aBitMeh = builder.aBitMeh;
    this.grumpy = builder.grumpy;
    this.stressedOutNotAHappyCamper = builder.stressedOutNotAHappyCamper;
    this.moodMessages = builder.moodMessages;
  }

  public int getHappy() {
    return happy;
  }

  public int getJustNormalReally() {
    return justNormalReally;
  }

  public int getABitMeh() {
    return aBitMeh;
  }

  public int getGrumpy() {
    return grumpy;
  }

  public int getStressedOutNotAHappyCamper() {
    return stressedOutNotAHappyCamper;
  }

  public List<String> getMoodMessages() {
    return moodMessages;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private int happy;

    private int justNormalReally;

    private int aBitMeh;

    private int grumpy;

    private int stressedOutNotAHappyCamper;

    private List<String> moodMessages = new ArrayList<>();

    private Builder() {}

    public Builder setHappy(int happy) {
      this.happy = happy;
      return this;
    }

    public Builder setJustNormalReally(int justNormalReally) {
      this.justNormalReally = justNormalReally;
      return this;
    }

    public Builder setaBitMeh(int aBitMeh) {
      this.aBitMeh = aBitMeh;
      return this;
    }

    public Builder setGrumpy(int grumpy) {
      this.grumpy = grumpy;
      return this;
    }

    public Builder setStressedOutNotAHappyCamper(int stressedOutNotAHappyCamper) {
      this.stressedOutNotAHappyCamper = stressedOutNotAHappyCamper;
      return this;
    }

    public Builder setMoodMessages(List<String> moodMessages) {
      this.moodMessages = new ArrayList<>(moodMessages);
      return this;
    }

    public SummaryResponse build() {
      return new SummaryResponse(this);
    }
  }
}

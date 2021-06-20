package com.fuel50.homework.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fuel50.homework.api.UnknownValueException;

public enum Mood {
  HAPPY(0),
  JUST_NORMAL_REALLY(1),
  A_BIT_MEH(2),
  GRUMPY(3),
  STRESSED_OUT_NOT_A_HAPPY_CAMPER(4);

  private int value;

  Mood(int value) {
    this.value = value;
  }

  @JsonValue
  public int getValue() {
    return value;
  }

  @JsonCreator
  public static Mood of(Integer value) {
    if (null == value) {
      return null;
    }

    for (Mood item : Mood.values()) {
      if (value.equals(item.getValue())) {
        return item;
      }
    }

    throw new UnknownValueException(String.format("Mood - unknown value: %s", value));
  }
}

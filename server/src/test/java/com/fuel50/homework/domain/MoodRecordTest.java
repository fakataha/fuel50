package com.fuel50.homework.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoodRecordTest {

  @Test
  @DisplayName("Records are equal.")
  void testEquals() {
    MoodRecord record1 = MoodRecord.builder().setMoodMessage("Test").setMood(Mood.GRUMPY).build();

    MoodRecord record2 = MoodRecord.builder().setMoodMessage("Test").setMood(Mood.GRUMPY).build();

    Assertions.assertThat(record1).isEqualTo(record2);
  }

  @Test
  @DisplayName("Records are not equal by mood.")
  void testNotEqualMood() {
    MoodRecord record1 = MoodRecord.builder().setMoodMessage("Test").setMood(Mood.GRUMPY).build();

    MoodRecord record2 =
        MoodRecord.builder().setMoodMessage("Test").setMood(Mood.A_BIT_MEH).build();

    Assertions.assertThat(record1).isNotEqualTo(record2);
  }

  @Test
  @DisplayName("Records are not equal by message.")
  void testNotEqualMessage() {
    MoodRecord record1 =
        MoodRecord.builder().setMoodMessage("Test 1").setMood(Mood.A_BIT_MEH).build();

    MoodRecord record2 =
        MoodRecord.builder().setMoodMessage("Test 2").setMood(Mood.A_BIT_MEH).build();

    Assertions.assertThat(record1).isNotEqualTo(record2);
  }
}

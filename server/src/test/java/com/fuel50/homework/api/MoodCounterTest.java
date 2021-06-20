package com.fuel50.homework.api;

import com.fuel50.homework.domain.Mood;
import com.fuel50.homework.domain.MoodRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MoodCounterTest {

  @ParameterizedTest(name = "Return correct count {1} for {0}")
  @MethodSource("moods")
  void testMoods(Mood mood, int count) {
    List<MoodRecord> moodRecords = getMoodRecords();

    MoodCounter counter = new MoodCounter();
    int result = counter.apply(moodRecords, mood);
    Assertions.assertThat(result).isEqualTo(count);
  }

  @Test
  @DisplayName("Null collections return 0.")
  void testNullCollection() {
    MoodCounter moodCounter = new MoodCounter();
    int result = moodCounter.apply(null, Mood.HAPPY);
    Assertions.assertThat(result).isEqualTo(0);
  }

  @Test
  @DisplayName("Null Moods return 0.")
  void testNullMood() {
    List<MoodRecord> moodRecords = getMoodRecords();

    MoodCounter moodCounter = new MoodCounter();
    int result = moodCounter.apply(moodRecords, null);
    Assertions.assertThat(result).isEqualTo(0);
  }

  private List<MoodRecord> getMoodRecords() {
    List<MoodRecord> moodRecords = new ArrayList<>();
    MoodRecord happy = MoodRecord.builder().setMood(Mood.HAPPY).build();
    moodRecords.add(happy);
    moodRecords.add(happy);
    moodRecords.add(happy);
    moodRecords.add(happy);
    moodRecords.add(happy);

    MoodRecord grumpy = MoodRecord.builder().setMood(Mood.GRUMPY).build();
    moodRecords.add(grumpy);
    moodRecords.add(grumpy);
    moodRecords.add(grumpy);
    moodRecords.add(grumpy);

    MoodRecord meh = MoodRecord.builder().setMood(Mood.A_BIT_MEH).build();
    moodRecords.add(meh);
    moodRecords.add(meh);
    moodRecords.add(meh);

    MoodRecord normal = MoodRecord.builder().setMood(Mood.JUST_NORMAL_REALLY).build();
    moodRecords.add(normal);
    moodRecords.add(normal);

    MoodRecord stressed =
        MoodRecord.builder().setMood(Mood.STRESSED_OUT_NOT_A_HAPPY_CAMPER).build();
    moodRecords.add(stressed);
    return moodRecords;
  }

  private static Stream<Arguments> moods() {
    return Stream.of(
        Arguments.of(Mood.HAPPY, 5),
        Arguments.of(Mood.GRUMPY, 4),
        Arguments.of(Mood.A_BIT_MEH, 3),
        Arguments.of(Mood.JUST_NORMAL_REALLY, 2),
        Arguments.of(Mood.STRESSED_OUT_NOT_A_HAPPY_CAMPER, 1),
        Arguments.of(null, 0));
  }
}

package com.fuel50.homework.api;

import com.fuel50.homework.domain.Mood;
import com.fuel50.homework.domain.MoodRecord;
import java.util.List;
import java.util.function.BiFunction;
import org.springframework.stereotype.Component;

/** Functional class to count occurrences of a specific mood in a collection. */
@Component
public class MoodCounter implements BiFunction<List<MoodRecord>, Mood, Integer> {

  @Override
  public Integer apply(List<MoodRecord> moodRecords, Mood mood) {
    if (moodRecords == null || moodRecords.isEmpty() || mood == null) {
      return 0;
    }
    long count = moodRecords.stream().filter(record -> mood.equals(record.getMood())).count();
    return Math.toIntExact(count);
  }
}

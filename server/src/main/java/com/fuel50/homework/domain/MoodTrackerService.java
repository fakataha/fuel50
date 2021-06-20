package com.fuel50.homework.domain;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodTrackerService {

  @Autowired private MoodTrackerRepository moodTrackerRepository;

  public List<MoodRecord> getAllMoodRecords() {
    List<MoodRecord> moodRecords = moodTrackerRepository.findAll();
    return moodRecords;
  }

  public List<MoodRecord> getCurrentMoodRecords() {
    OffsetDateTime startOfDay = OffsetDateTime.now().with(ChronoField.HOUR_OF_DAY, 0).with(ChronoField.MINUTE_OF_DAY, 0);
    List<MoodRecord> moodRecords =
        moodTrackerRepository.findAllWithCreationDateTimeAfter(startOfDay);
    return moodRecords;
  }

  public MoodRecord add(MoodRecord moodRecord) {
    MoodRecord storedRecord = moodTrackerRepository.save(moodRecord);
    return storedRecord;
  }
}

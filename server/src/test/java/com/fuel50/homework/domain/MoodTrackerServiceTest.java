package com.fuel50.homework.domain;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MoodTrackerServiceTest {

  @Mock private MoodTrackerRepository moodTrackerRepository;

  @InjectMocks MoodTrackerService moodTrackerService;

  @Test
  @DisplayName("Get all mood records.")
  void testGetAllMoodRecords() {
    List<MoodRecord> moodRecords = new ArrayList<>();
    MoodRecord moodRecord = MoodRecord.builder().build();
    moodRecords.add(moodRecord);

    Mockito.when(moodTrackerRepository.findAll()).thenReturn(moodRecords);

    List<MoodRecord> result = moodTrackerService.getAllMoodRecords();

    Assertions.assertThat(result).isNotNull().isNotEmpty().hasSize(1).containsOnly(moodRecord);
  }

  @Test
  @DisplayName("Get only the current dates records.")
  void testGetCurrent() {
    List<MoodRecord> moodRecords = new ArrayList<>();
    MoodRecord moodRecord = MoodRecord.builder().build();
    moodRecords.add(moodRecord);

    Mockito.when(moodTrackerRepository.findAllWithCreationDateTimeAfter(Mockito.any()))
        .thenReturn(moodRecords);

    List<MoodRecord> result = moodTrackerService.getCurrentMoodRecords();

    Assertions.assertThat(result).isNotNull().isNotEmpty().hasSize(1).containsOnly(moodRecord);

    ArgumentCaptor<OffsetDateTime> captor = ArgumentCaptor.forClass(OffsetDateTime.class);
    Mockito.verify(moodTrackerRepository).findAllWithCreationDateTimeAfter(captor.capture());
    OffsetDateTime capturedDateTime = captor.getValue();

    OffsetDateTime expected = OffsetDateTime.now().with(ChronoField.HOUR_OF_DAY, 0);
    Assertions.assertThat(capturedDateTime.truncatedTo(ChronoUnit.HOURS))
        .isEqualTo(expected.truncatedTo(ChronoUnit.HOURS));
  }

  @Test
  @DisplayName("Add a new record successfully.")
  void testAdd() {
    MoodRecord moodRecord =
        MoodRecord.builder().setMood(Mood.JUST_NORMAL_REALLY).setMoodMessage("TEST").build();

    Mockito.when(moodTrackerRepository.save(Mockito.any())).thenReturn(moodRecord);

    MoodRecord result = moodTrackerService.add(moodRecord);

    Assertions.assertThat(result)
        .isNotNull()
        .extracting("mood", "moodMessage")
        .containsOnly(Mood.JUST_NORMAL_REALLY, "TEST");
  }
}

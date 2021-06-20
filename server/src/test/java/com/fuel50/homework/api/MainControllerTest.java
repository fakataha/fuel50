package com.fuel50.homework.api;

import com.fuel50.homework.api.model.StatusResponse;
import com.fuel50.homework.api.model.SuccessResponse;
import com.fuel50.homework.api.model.SummaryResponse;
import com.fuel50.homework.domain.Mood;
import com.fuel50.homework.domain.MoodRecord;
import com.fuel50.homework.domain.MoodTrackerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Mock
    private MoodTrackerService moodTrackerService;

    @Mock private MoodCounter moodCounter;

    @InjectMocks
    private MainController mainController;

    @Test
    @DisplayName("Successful ping.")
    void testPing() {
        List<MoodRecord> moodRecords = new ArrayList<>();
        moodRecords.add(MoodRecord.builder()
                .setMood(Mood.HAPPY)
                .setMoodMessage("It might seem crazy what I'm about to say")
                .build());
        Mockito.when(moodTrackerService.getAllMoodRecords()).thenReturn(moodRecords);

        StatusResponse result = mainController.getStatus();

        Assertions.assertThat(result).isNotNull()
                .extracting("numberOfRecords", "up")
                .containsOnly(1, true);
    }

    @Test
    @DisplayName("Successful summary.")
    void testSummary() {
        List<MoodRecord> moodRecords = new ArrayList<>();
        moodRecords.add(MoodRecord.builder()
                .setMood(Mood.HAPPY)
                .setMoodMessage("Sunshine she's here, you can take a break")
                .build());
        Mockito.when(moodTrackerService.getCurrentMoodRecords()).thenReturn(moodRecords);

        Mockito.when(moodCounter.apply(Mockito.any(), Mockito.any()))
                .thenReturn(999, 2, 5, 7, 3);

        SummaryResponse result = mainController.getSummary();

        Assertions.assertThat(result).isNotNull()
                .extracting("happy", "justNormalReally", "aBitMeh", "grumpy", "stressedOutNotAHappyCamper")
                .containsExactly(999, 2, 5, 7, 3);

        Assertions.assertThat(result.getMoodMessages()).isNotEmpty()
                .containsOnly("Sunshine she's here, you can take a break");
    }

    @Test
    @DisplayName("Successfully add a new record.")
    void testAdd() throws Exception {
        MoodRecord moodRecord = MoodRecord.builder()
                .setMood(Mood.HAPPY)
                .setMoodMessage("I'm a hot air balloon that could go to space")
                .build();

        Mockito.when(moodTrackerService.add(Mockito.any())).thenReturn(moodRecord);

        SuccessResponse result = mainController.addMoodRecord(moodRecord);

        Assertions.assertThat(result).isNotNull()
                .extracting("httpStatusCode", "message", "body")
                .containsExactly(200, "Recorded successfully!", moodRecord);
    }

    @Test
    @DisplayName("Record creation is not successful.")
    void testFailToAdd() {
        MoodRecord moodRecord = MoodRecord.builder()
                .setMood(Mood.HAPPY)
                .setMoodMessage("I'm a hot air balloon that could go to space")
                .build();

        Mockito.when(moodTrackerService.add(Mockito.any())).thenReturn(null);

        Assertions.assertThatThrownBy(() -> mainController.addMoodRecord(moodRecord))
                .isInstanceOf(ResponseStatusException.class)
                .withFailMessage("Something has gone wrong. Please try again later.");
    }
}

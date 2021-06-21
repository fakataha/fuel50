package com.fuel50.homework.api;

import com.fuel50.homework.api.model.StatusResponse;
import com.fuel50.homework.api.model.SuccessResponse;
import com.fuel50.homework.api.model.SummaryResponse;
import com.fuel50.homework.domain.Mood;
import com.fuel50.homework.domain.MoodRecord;
import com.fuel50.homework.domain.MoodTrackerService;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(originPatterns = "http://localhost*", allowCredentials = "true")
public class MainController {

  private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

  @Autowired private MoodTrackerService moodTrackerService;

  @Autowired private MoodCounter moodCounter;

  @GetMapping("/v1/ping")
  public StatusResponse getStatus() {
    List<MoodRecord> moodRecords = moodTrackerService.getAllMoodRecords();
    int numberOfRecords = moodRecords.size();
    OffsetDateTime lastRecordDateTime = null;
    if (!moodRecords.isEmpty()) {
      MoodRecord lastRecord = moodRecords.get(numberOfRecords - 1);
      lastRecordDateTime = lastRecord.getCreationDateTime();
    }
    return StatusResponse.builder()
        .setLastRecordEvent(lastRecordDateTime)
        .setUp(true)
        .setNumberOfRecords(numberOfRecords)
        .build();
  }

  @GetMapping("/v1/mood/summary")
  public SummaryResponse getSummary() {
    List<MoodRecord> moodRecords = moodTrackerService.getCurrentMoodRecords();

    int happy = moodCounter.apply(moodRecords, Mood.HAPPY);
    int justNormalReally = moodCounter.apply(moodRecords, Mood.JUST_NORMAL_REALLY);
    int aBitMeh = moodCounter.apply(moodRecords, Mood.A_BIT_MEH);
    int grumpy = moodCounter.apply(moodRecords, Mood.GRUMPY);
    int stressedOutNotAHappyCamper =
        moodCounter.apply(moodRecords, Mood.STRESSED_OUT_NOT_A_HAPPY_CAMPER);

    List<String> moodMessages =
        moodRecords.stream()
            .map(MoodRecord::getMoodMessage)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

    return SummaryResponse.builder()
        .setHappy(happy)
        .setJustNormalReally(justNormalReally)
        .setaBitMeh(aBitMeh)
        .setGrumpy(grumpy)
        .setStressedOutNotAHappyCamper(stressedOutNotAHappyCamper)
        .setMoodMessages(moodMessages)
        .build();
  }

  @PostMapping("/v1/mood")
  public SuccessResponse addMoodRecord(@RequestBody MoodRecord moodRecord) {
    LOG.info(moodRecord.toString());
    MoodRecord storedRecord = moodTrackerService.add(moodRecord);
    if (storedRecord != null) {
      return SuccessResponse.builder()
          .setHttpStatusCode(HttpStatus.CREATED.value())
          .setBody(storedRecord)
          .setMessage("Recorded successfully!")
          .build();
    } else {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong. Please try again later.");
    }
  }
}

package com.fuel50.homework.api.model;

import com.fuel50.homework.domain.MoodRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SuccessResponseTest {

  @Test
  @DisplayName("Builder sets fields correctly.")
  void testBuild() {
    MoodRecord record = MoodRecord.builder().build();

    SuccessResponse result =
        SuccessResponse.builder()
            .setMessage("This is a test!")
            .setHttpStatusCode(200)
            .setBody(record)
            .build();

    Assertions.assertThat(result)
        .isNotNull()
        .extracting("message", "httpStatusCode", "body")
        .containsOnly("This is a test!", 200, record);
  }

  @Test
  @DisplayName("Defaults for field types are correct.")
  void testDefaults() {
    SuccessResponse result = SuccessResponse.builder().build();

    Assertions.assertThat(result)
        .isNotNull()
        .extracting("message", "httpStatusCode", "body")
        .containsOnly(null, 0, null);
  }
}

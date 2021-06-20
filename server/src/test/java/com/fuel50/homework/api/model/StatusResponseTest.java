package com.fuel50.homework.api.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

class StatusResponseTest {

    @Test
    @DisplayName("Builder sets fields correctly.")
    void testBuild() {
        OffsetDateTime now = OffsetDateTime.now();

        StatusResponse result = StatusResponse.builder()
                .setNumberOfRecords(599)
                .setUp(true)
                .setLastRecordEvent(now)
                .build();

        Assertions.assertThat(result)
                .isNotNull()
                .extracting("numberOfRecords", "up", "lastRecordEvent")
                .containsOnly(599, true, now);
    }

    @Test
    @DisplayName("Defaults for field types are correct.")
    void testDefaults() {
        StatusResponse result = StatusResponse.builder()
                .build();

        Assertions.assertThat(result)
                .isNotNull()
                .extracting("numberOfRecords", "up", "lastRecordEvent")
                .containsOnly(0, false, null);
    }
}
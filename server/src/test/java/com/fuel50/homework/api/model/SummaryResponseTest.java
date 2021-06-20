package com.fuel50.homework.api.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SummaryResponseTest {


    @Test
    @DisplayName("Builder sets fields correctly.")
    void testBuild() {
        List<String> moodMessages = Arrays.asList("This is test 1.", "Another test message", "One more!");

        SummaryResponse result = SummaryResponse.builder()
                .setMoodMessages(moodMessages)
                .setStressedOutNotAHappyCamper(999)
                .setGrumpy(4)
                .setHappy(888)
                .setJustNormalReally(32)
                .setaBitMeh(678)
                .build();

        Assertions.assertThat(result).isNotNull()
                .extracting("moodMessages", "stressedOutNotAHappyCamper", "grumpy", "happy", "justNormalReally", "aBitMeh")
                .containsOnly(moodMessages, 999, 4, 888, 32, 678);
    }

    @Test
    @DisplayName("Defaults for field types are correct.")
    void testDefaults() {
        SummaryResponse result = SummaryResponse.builder()
                .build();

        Assertions.assertThat(result).isNotNull()
                .extracting("moodMessages", "stressedOutNotAHappyCamper", "grumpy", "happy", "justNormalReally", "aBitMeh")
                .containsOnly(new ArrayList<>(), 0, 0, 0, 0, 0);
    }

    @Test
    @DisplayName("Changes to original collection does not affect response model.")
    void testReference() {
        List<String> moodMessages = new ArrayList<>();
        moodMessages.add("This is test 1.");
        moodMessages.add("Another test message");
        moodMessages.add("One more.");

        SummaryResponse result = SummaryResponse.builder()
                .setMoodMessages(moodMessages)
                .build();

        Assertions.assertThat(result.getMoodMessages())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);

        moodMessages.add("NEW MESSAGE");

        Assertions.assertThat(result.getMoodMessages())
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);
    }
}
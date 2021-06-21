package com.fuel50.homework.integrations;

import static io.restassured.RestAssured.given;

import com.fuel50.homework.domain.Mood;
import com.fuel50.homework.domain.MoodRecord;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoodTrackerIntegrationTest {

  @Test
  @DisplayName("Successfully retrieve all records.")
  void testGetSummarySuccessfully() {
    given()
        .when()
        .get("http://localhost:8081/api/v1/mood/summary")
        .then()
        .statusCode(200)
        .log()
        .all();
  }

  @Test
  @DisplayName("Successfully add a new record.")
  void testAddSuccessfully() {
    MoodRecord moodRecord =
        MoodRecord.builder().setMoodMessage("TESTING").setMood(Mood.HAPPY).build();
    given()
        .body(moodRecord)
            .contentType(ContentType.JSON)
        .when()
        .post("http://localhost:8081/api/v1/mood")
        .then()
        .statusCode(200)
        .log()
        .all();
  }

  @Test
  @DisplayName("Successfully test ping response.")
  void testPingSuccessfully() {
    given().when().get("http://localhost:8081/api/v1/ping").then().statusCode(200).log().all();
  }
}

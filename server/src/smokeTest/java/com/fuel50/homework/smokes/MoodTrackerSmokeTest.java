package com.fuel50.homework.smokes;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoodTrackerSmokeTest {

  @Test
  @DisplayName("Successfully test ping response.")
  void testPingSuccessfully() {
    // Get URL from CI/CD
    given().when().get("http://localhost:8081/ping").then().statusCode(200).log().all();
  }
}

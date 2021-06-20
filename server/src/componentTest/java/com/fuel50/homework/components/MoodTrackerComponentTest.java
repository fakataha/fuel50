package com.fuel50.homework.components;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoodTrackerComponentTest {

  @Test
  @DisplayName("Retrieve successful response from ping.")
  void testSuccess() {
    given().when().get("/ping").then().statusCode(200).log().all();
  }
}

package com.fuel50.homework.api;

import com.fuel50.homework.api.model.StatusResponse;
import com.fuel50.homework.domain.ExampleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = "http://localhost*", allowCredentials = "true")
public class MainController {

  private final ExampleEntityService exampleEntityService;

  @Autowired
  MainController(ExampleEntityService exampleEntityService) {
    this.exampleEntityService = exampleEntityService;
  }

  @GetMapping("/ping")
  public StatusResponse getStatus() {
      return new StatusResponse(true, exampleEntityService.getAllFoo());
  }

}

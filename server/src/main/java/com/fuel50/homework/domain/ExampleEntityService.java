package com.fuel50.homework.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExampleEntityService {

  private final ExampleEntityRepository exampleEntityRepository;

  @Autowired
  public ExampleEntityService(ExampleEntityRepository exampleEntityRepository) {
    this.exampleEntityRepository = exampleEntityRepository;
  }

  public List<String> getAllFoo() {
    List<ExampleEntity> exampleEntities = exampleEntityRepository.findAll();

    return exampleEntities.stream()
            .map(ExampleEntity::getFoo)
            .collect(Collectors.toList());
  }
}

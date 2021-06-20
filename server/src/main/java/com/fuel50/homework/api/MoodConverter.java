package com.fuel50.homework.api;

import com.fuel50.homework.domain.Mood;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MoodConverter implements Converter<String, Mood> {
  @Override
  public Mood convert(String value) {
    return Mood.of(Integer.valueOf(value));
  }
}

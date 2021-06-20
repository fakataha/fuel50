package com.fuel50.homework.domain;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.*;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "MOOD_RECORD")
public class MoodRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "moodMessage", length = 350)
  @Nullable
  private String moodMessage;

  @Column(name = "creationDateTime")
  private OffsetDateTime creationDateTime = OffsetDateTime.now();

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "mood")
  private Mood mood;

  public MoodRecord() {}

  private MoodRecord(Builder builder) {
    this.mood = builder.mood;
    this.moodMessage = builder.moodMessage;
  }

  public Long getId() {
    return id;
  }

  @Nullable
  public String getMoodMessage() {
    return moodMessage;
  }

  public OffsetDateTime getCreationDateTime() {
    return creationDateTime;
  }

  public Mood getMood() {
    return mood;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MoodRecord that = (MoodRecord) o;
    return Objects.equals(id, that.id)
        && Objects.equals(moodMessage, that.moodMessage)
        && Objects.equals(mood, that.mood);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, moodMessage, mood, creationDateTime);
  }

  public static final class Builder {

    private String moodMessage;

    private Mood mood;

    private Builder() {}

    public Builder setMoodMessage(String moodMessage) {
      this.moodMessage = moodMessage;
      return this;
    }

    public Builder setMood(Mood mood) {
      this.mood = mood;
      return this;
    }

    public MoodRecord build() {
      return new MoodRecord(this);
    }
  }
}

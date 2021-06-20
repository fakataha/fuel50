package com.fuel50.homework.domain;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoodTrackerRepository extends JpaRepository<MoodRecord, Long> {

  @Query("select * from MoodRecord r where r.creationDateTime >= :creationDateTime")
  List<MoodRecord> findAllWithCreationDateTimeAfter(
      @Param("creationDateTime") OffsetDateTime creationDateTime);
}

package com.fuel50.homework.domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "example")
public class ExampleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "foo", length = 350)
  @Nullable
  private String foo;

  public ExampleEntity(String foo) {
    this.foo = foo;
  }

  public Long getId() {
    return id;
  }

  public String getFoo() {
    return foo;
  }

  public ExampleEntity() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExampleEntity that = (ExampleEntity) o;
    return Objects.equals(id, that.id)
        && foo == that.foo;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, foo);
  }
}

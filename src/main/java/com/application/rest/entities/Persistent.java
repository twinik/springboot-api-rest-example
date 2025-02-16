package com.application.rest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Persistent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime created_at;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime updated_at;

  @Column(name = "activo")
  private boolean active = true;

  protected void setId(Long id) {
    this.id = id;
  }

  public void logicalDelete() {
    this.active = false;
  }

  @PrePersist
  protected void onCreate() {
    this.created_at = LocalDateTime.now();
    this.updated_at = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = LocalDateTime.now();
  }
}

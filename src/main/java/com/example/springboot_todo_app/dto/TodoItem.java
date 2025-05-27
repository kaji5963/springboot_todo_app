package com.example.springboot_todo_app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class TodoItem {
  @NonNull
  private Long id;
  @NonNull
  private String title;
  @NonNull
  private String description;
  @NonNull
  private LocalDateTime createdAt;
  @NonNull
  private LocalDateTime updatedAt;
}
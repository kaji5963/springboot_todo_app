package com.example.springboot_todo_app.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * Todoアイテム更新時のリクエストDTO
 */
@Data
public class TodoUpdateRequest {
  /**
   * Todoアイテムのタイトル
   */
  @NotBlank(message = "タイトルは必須です")
  private String title;

  /**
   * Todoアイテムの説明
   */
  @NotBlank(message = "説明は必須です")
  private String description;

  /**
   * Todoアイテムの完了状態
   * true: 完了
   * false: 未完了
   */
  private boolean completed;
}
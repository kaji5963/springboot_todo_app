package com.example.springboot_todo_app.dto;

import lombok.Data;

/**
 * Todoアイテム登録用のリクエストDTO
 * フロントエンドから送信される登録データをマッピングします
 */
@Data
public class TodoRegisterRequest {
  /**
   * Todoアイテムのタイトル
   */
  private String title;

  /**
   * Todoアイテムの説明
   */
  private String description;
}
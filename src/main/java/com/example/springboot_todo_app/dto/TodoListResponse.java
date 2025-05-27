package com.example.springboot_todo_app.dto;

import com.example.springboot_todo_app.entity.TodoItem;
import lombok.Data;
import java.util.List;

/**
 * Todoアイテムのリストを返すためのレスポンスDTO
 * フロントエンドにデータを渡す際に使用されます
 */
@Data
public class TodoListResponse {
  /**
   * Todoアイテムのリスト
   */
  private List<TodoItem> todos;

  /**
   * コンストラクタ
   *
   * @param todos Todoアイテムのリスト
   */
  public TodoListResponse(List<TodoItem> todos) {
    this.todos = todos;
  }
}
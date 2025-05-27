package com.example.springboot_todo_app.service;

import com.example.springboot_todo_app.entity.TodoItem;
import java.util.List;

/**
 * Todoアイテムのビジネスロジックを定義するインターフェース
 */
public interface TodoService {
  /**
   * すべてのTodoアイテムを取得します
   *
   * @return Todoアイテムのリスト
   */
  List<TodoItem> getAllTodos();
}
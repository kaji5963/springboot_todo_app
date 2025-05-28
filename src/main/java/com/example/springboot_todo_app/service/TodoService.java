package com.example.springboot_todo_app.service;

import com.example.springboot_todo_app.entity.TodoItem;
import java.util.List;

/**
 * Todoアイテムのビジネスロジックを定義するインターフェース
 */
public interface TodoService {
  /**
   * 指定されたIDのTodoアイテムを取得します
   *
   * @param id TodoアイテムのID
   * @return 指定されたIDのTodoアイテム
   */
  TodoItem getTodo(Long id);

  /**
   * すべてのTodoアイテムを取得します
   * 作成日時の降順（新しい順）でソートされます
   *
   * @return 作成日時の降順でソートされたTodoアイテムのリスト
   */
  List<TodoItem> getAllTodos();

  /**
   * 新しいTodoアイテムを登録します
   *
   * @param title       Todoアイテムのタイトル
   * @param description Todoアイテムの説明
   * @return 登録されたTodoアイテム
   */
  TodoItem registerTodo(String title, String description);
}
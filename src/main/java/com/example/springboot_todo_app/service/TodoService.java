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
   * @param id 取得するTodoアイテムのID
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

  /**
   * 指定されたIDのTodoアイテムを更新します
   * アイテムが存在しない場合は例外をスローします
   *
   * @param id          更新するTodoアイテムのID
   * @param title       新しいタイトル
   * @param description 新しい説明
   * @return 更新されたTodoアイテム
   * @throws RuntimeException 指定されたIDのTodoアイテムが存在しない場合
   */
  TodoItem updateTodo(Long id, String title, String description);

  /**
   * 指定されたIDのTodoアイテムを削除します
   * アイテムが存在しない場合は例外をスローします
   *
   * @param id 削除するTodoアイテムのID
   * @throws RuntimeException 指定されたIDのTodoアイテムが存在しない場合
   */
  void deleteTodo(Long id);
}
package com.example.springboot_todo_app.repository;

import com.example.springboot_todo_app.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Todoアイテムのリポジトリインターフェース
 * データベースアクセスに関する操作を提供します
 */
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
  /**
   * すべてのTodoアイテムを作成日時の降順で取得します
   *
   * @return 作成日時の降順でソートされたTodoアイテムのリスト
   */
  List<TodoItem> findAllByOrderByCreatedAtDesc();
}
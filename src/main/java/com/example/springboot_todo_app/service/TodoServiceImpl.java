package com.example.springboot_todo_app.service;

import com.example.springboot_todo_app.entity.TodoItem;
import com.example.springboot_todo_app.repository.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * TodoServiceの実装クラス
 * Todoアイテムの取得、作成、更新、削除などの操作を提供します
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

  private final TodoItemRepository todoItemRepository;

  /**
   * {@inheritDoc}
   * 指定されたIDのTodoアイテムを取得します。
   * アイテムが存在しない場合は例外をスローします。
   *
   * @param id 取得するTodoアイテムのID
   * @return 指定されたIDのTodoアイテム
   * @throws RuntimeException 指定されたIDのTodoアイテムが存在しない場合
   */
  @Override
  public TodoItem getTodo(Long id) {
    return todoItemRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
  }

  /**
   * {@inheritDoc}
   * リポジトリからすべてのTodoアイテムを取得し、作成日時の降順でソートします
   */
  @Override
  public List<TodoItem> getAllTodos() {
    return todoItemRepository.findAllByOrderByCreatedAtDesc();
  }

  /**
   * {@inheritDoc}
   * 新しいTodoアイテムを作成し、リポジトリに保存します
   */
  @Override
  public TodoItem registerTodo(String title, String description) {
    TodoItem todoItem = new TodoItem();
    todoItem.setTitle(title);
    todoItem.setDescription(description);
    return todoItemRepository.save(todoItem);
  }
}
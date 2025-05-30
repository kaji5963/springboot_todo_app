package com.example.springboot_todo_app.service;

import com.example.springboot_todo_app.entity.TodoItem;
import com.example.springboot_todo_app.repository.TodoItemRepository;
import com.example.springboot_todo_app.exception.TodoNotFoundException;
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
   * @throws TodoNotFoundException 指定されたIDのTodoアイテムが存在しない場合
   */
  @Override
  public TodoItem getTodo(Long id) {
    return todoItemRepository.findById(id)
        .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
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
   * 登録時は常に未完了（false）状態で作成されます
   */
  @Override
  public TodoItem registerTodo(String title, String description) {
    TodoItem todoItem = new TodoItem();
    todoItem.setTitle(title);
    todoItem.setDescription(description);
    todoItem.setCompleted(false); // 常に未完了状態で作成
    return todoItemRepository.save(todoItem);
  }

  /**
   * {@inheritDoc}
   * 指定されたIDのTodoアイテムを更新します。
   * アイテムが存在しない場合は例外をスローします。
   *
   * @param id          更新するTodoアイテムのID
   * @param title       新しいタイトル
   * @param description 新しい説明
   * @param completed   新しい完了状態
   * @return 更新されたTodoアイテム
   * @throws TodoNotFoundException 指定されたIDのTodoアイテムが存在しない場合
   */
  @Override
  public TodoItem updateTodo(Long id, String title, String description, boolean completed) {
    TodoItem targetTodo = todoItemRepository.findById(id)
        .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

    targetTodo.setTitle(title);
    targetTodo.setDescription(description);
    targetTodo.setCompleted(completed);

    return todoItemRepository.save(targetTodo);
  }

  /**
   * {@inheritDoc}
   * 指定されたIDのTodoアイテムを削除します。
   * アイテムが存在しない場合は例外をスローします。
   *
   * @param id 削除するTodoアイテムのID
   * @throws TodoNotFoundException 指定されたIDのTodoアイテムが存在しない場合
   */
  @Override
  public void deleteTodo(Long id) {
    if (!todoItemRepository.existsById(id)) {
      throw new TodoNotFoundException("Todo not found with id: " + id);
    }
    todoItemRepository.deleteById(id);
  }
}
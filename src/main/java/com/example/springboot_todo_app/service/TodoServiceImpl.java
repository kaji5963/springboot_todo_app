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
   * リポジトリからすべてのTodoアイテムを取得します
   */
  @Override
  public List<TodoItem> getAllTodos() {
    return todoItemRepository.findAll();
  }
}
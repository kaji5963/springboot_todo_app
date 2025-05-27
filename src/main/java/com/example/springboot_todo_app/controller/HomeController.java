package com.example.springboot_todo_app.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.springboot_todo_app.service.TodoService;
import lombok.RequiredArgsConstructor;
import com.example.springboot_todo_app.dto.TodoListResponse;

/**
 * Todoアプリケーションのホーム画面用コントローラー
 * Todoアイテムの一覧表示機能を提供します
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Next.jsのデフォルトポート
public class HomeController {

  private final TodoService todoService;

  /**
   * すべてのTodoアイテムを取得するエンドポイント
   *
   * @return Todoアイテムのリストを含むレスポンス
   */
  @GetMapping("/")
  public TodoListResponse home() {
    return new TodoListResponse(todoService.getAllTodos());
  }
}
package com.example.springboot_todo_app.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.springboot_todo_app.service.TodoService;
import lombok.RequiredArgsConstructor;
import com.example.springboot_todo_app.dto.TodoRegisterRequest;
import com.example.springboot_todo_app.dto.TodoUpdateRequest;
import com.example.springboot_todo_app.entity.TodoItem;

/**
 * Todoアイテムの操作に関するコントローラー
 * 登録、更新、削除などの操作を提供します
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/todos")
public class TodoController {

  private final TodoService todoService;

  /**
   * 新しいTodoアイテムを登録するエンドポイント
   *
   * @param request 登録するTodoアイテムの情報
   * @return 登録されたTodoアイテム
   */
  @PostMapping("/register")
  public TodoItem registerTodo(@RequestBody TodoRegisterRequest request) {
    return todoService.registerTodo(request.getTitle(), request.getDescription());
  }

  /**
   * 指定されたIDのTodoアイテムを取得するエンドポイント
   *
   * @param id TodoアイテムのID（文字列形式）
   * @return 指定されたIDのTodoアイテム
   */
  @GetMapping("/{id}/edit")
  public TodoItem getTodo(@PathVariable String id) {
    return todoService.getTodo(Long.parseLong(id));
  }

  /**
   * 指定されたIDのTodoアイテムを更新するエンドポイント
   *
   * @param id      更新するTodoアイテムのID（文字列形式）
   * @param request 更新するTodoアイテムの情報（タイトルと説明）
   * @return 更新されたTodoアイテム
   */
  @PutMapping("/{id}/update")
  public TodoItem updateTodo(@PathVariable String id, @RequestBody TodoUpdateRequest request) {
    return todoService.updateTodo(Long.parseLong(id), request.getTitle(), request.getDescription());
  }
}
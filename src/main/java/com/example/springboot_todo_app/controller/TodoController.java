package com.example.springboot_todo_app.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.example.springboot_todo_app.service.TodoService;
import lombok.RequiredArgsConstructor;

import com.example.springboot_todo_app.dto.TodoListResponse;
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
@RequestMapping("/")
@Validated
public class TodoController {

  private final TodoService todoService;

  /**
   * すべてのTodoアイテムを取得するエンドポイント
   * ルートパス（/）へのアクセスでTodo一覧を表示します
   *
   * @return Todoアイテムのリストを含むレスポンス
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public TodoListResponse getAllTodos() {
    return new TodoListResponse(todoService.getAllTodos());
  }

  /**
   * 新しいTodoアイテムを登録するエンドポイント
   *
   * @param request 登録するTodoアイテムの情報
   * @return 登録されたTodoアイテム
   */
  @PostMapping("/todos/register")
  @ResponseStatus(HttpStatus.CREATED)
  public TodoItem registerTodo(@Validated @RequestBody TodoRegisterRequest request) {
    return todoService.registerTodo(request.getTitle(), request.getDescription());
  }

  /**
   * 指定されたIDのTodoアイテムを取得するエンドポイント
   *
   * @param id TodoアイテムのID（文字列形式）
   * @return 指定されたIDのTodoアイテム
   */
  @GetMapping("/todos/{id}/edit")
  @ResponseStatus(HttpStatus.OK)
  public TodoItem getTodo(
      @PathVariable @NotBlank @Pattern(regexp = "^[0-9]+$", message = "ID must be numeric") String id) {
    return todoService.getTodo(Long.parseLong(id));
  }

  /**
   * 指定されたIDのTodoアイテムを更新するエンドポイント
   *
   * @param id      更新するTodoアイテムのID（文字列形式）
   * @param request 更新するTodoアイテムの情報（タイトルと説明）
   * @return 更新されたTodoアイテム
   */
  @PutMapping("/todos/{id}/update")
  @ResponseStatus(HttpStatus.OK)
  public TodoItem updateTodo(
      @PathVariable @NotBlank @Pattern(regexp = "^[0-9]+$", message = "ID must be numeric") String id,
      @Validated @RequestBody TodoUpdateRequest request) {
    return todoService.updateTodo(Long.parseLong(id), request.getTitle(), request.getDescription());
  }

  /**
   * 指定されたIDのTodoアイテムを削除するエンドポイント
   *
   * @param id 削除するTodoアイテムのID（文字列形式）
   * @return 削除後のTodoアイテムリスト
   */
  @DeleteMapping("/todos/{id}/delete")
  @ResponseStatus(HttpStatus.OK)
  public TodoListResponse deleteTodo(
      @PathVariable @NotBlank @Pattern(regexp = "^[0-9]+$", message = "ID must be numeric") String id) {
    todoService.deleteTodo(Long.parseLong(id));
    return new TodoListResponse(todoService.getAllTodos());
  }
}
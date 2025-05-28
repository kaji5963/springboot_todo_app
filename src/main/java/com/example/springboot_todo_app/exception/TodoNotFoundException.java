package com.example.springboot_todo_app.exception;

/**
 * Todoアイテムが見つからない場合にスローされる例外
 */
public class TodoNotFoundException extends RuntimeException {
  public TodoNotFoundException(String message) {
    super(message);
  }
}
package com.example.springboot_todo_app.dto;

import lombok.Data;
import java.util.List;

@Data
public class TodoListResponse {
  private List<TodoItem> todos;

  public TodoListResponse(List<TodoItem> todos) {
    this.todos = todos;
  }
}
package com.example.springboot_todo_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.springboot_todo_app.dto.TodoItem;
import com.example.springboot_todo_app.dto.TodoListResponse;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Next.jsのデフォルトポート
public class HomeController {

  @GetMapping("/")
  public TodoListResponse home() {
    LocalDateTime now = LocalDateTime.now();

    TodoItem todo1 = new TodoItem(
        1L,
        "買い物リストの作成",
        "週末の買い物に必要なものをリストアップする",
        now.minusDays(1),
        now.minusHours(2));

    TodoItem todo2 = new TodoItem(
        2L,
        "プロジェクトの進捗報告",
        "今週の進捗状況をまとめて、チームに共有する",
        now.minusDays(2),
        now.minusHours(5));

    return new TodoListResponse(Arrays.asList(todo1, todo2));
  }
}
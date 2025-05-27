package com.example.springboot_todo_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * Todoアイテムを表すエンティティクラス
 * データベースのtodoテーブルとマッピングされます
 */
@Entity
@Table(name = "todo")
@Data
public class TodoItem {
  /**
   * Todoアイテムの一意識別子
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Todoアイテムのタイトル
   */
  @Column(nullable = false)
  private String title;

  /**
   * Todoアイテムの詳細説明
   */
  @Column(nullable = false)
  private String description;

  /**
   * Todoアイテムの作成日時
   */
  @Column(name = "created_at", nullable = false)
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime createdAt;

  /**
   * Todoアイテムの最終更新日時
   */
  @Column(name = "updated_at", nullable = false)
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime updatedAt;

  /**
   * エンティティが永続化される前に実行されるメソッド
   * 作成日時と更新日時を現在時刻に設定します
   */
  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  /**
   * エンティティが更新される前に実行されるメソッド
   * 更新日時を現在時刻に設定します
   */
  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
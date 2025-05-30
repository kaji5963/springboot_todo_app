package com.example.springboot_todo_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * ユーザーを表すエンティティクラス
 * データベースのuserテーブルとマッピングされます
 */
@Entity
@Table(name = "user")
@Data
public class User {
  /**
   * ユーザーの一意識別子
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * ユーザー名
   */
  @Column(nullable = false, length = 255)
  private String name;

  /**
   * メールアドレス
   * 一意制約が付与されています
   */
  @Column(nullable = false, unique = true, length = 255)
  private String email;

  /**
   * パスワード
   */
  @Column(nullable = false, length = 255)
  private String password;

  /**
   * パスワード確認用
   */
  @Column(name = "password_confirm", nullable = false, length = 255)
  private String passwordConfirm;

  /**
   * ユーザーの作成日時
   */
  @Column(name = "created_at", nullable = false, updatable = false)
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime createdAt;

  /**
   * ユーザーの最終更新日時
   */
  @Column(name = "updated_at", nullable = false)
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime updatedAt;

  /**
   * リメンバートークン
   * ログイン状態を保持するために使用
   */
  @Column(name = "remember_token", length = 255)
  private String rememberToken;

  /**
   * リメンバートークンの有効期限
   */
  @Column(name = "remember_token_expires_at")
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime rememberTokenExpiresAt;

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
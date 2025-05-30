package com.example.springboot_todo_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * ユーザーログイン時のリクエストDTO
 */
@Data
public class UserLoginRequest {
  /**
   * メールアドレス
   */
  @NotBlank(message = "メールアドレスは必須です")
  @Email(message = "有効なメールアドレスを入力してください")
  private String email;

  /**
   * パスワード
   */
  @NotBlank(message = "パスワードは必須です")
  private String password;

  /**
   * ログイン状態を保持するかどうか
   */
  private boolean rememberMe;
}
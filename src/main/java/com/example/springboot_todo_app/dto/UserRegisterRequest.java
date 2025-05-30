package com.example.springboot_todo_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザー登録時のリクエストDTO
 */
@Data
public class UserRegisterRequest {
  /**
   * ユーザー名
   */
  @NotBlank(message = "ユーザー名は必須です")
  @Size(min = 2, max = 50, message = "ユーザー名は2文字以上50文字以下で入力してください")
  private String name;

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
  @Size(min = 8, message = "パスワードは8文字以上で入力してください")
  private String password;

  /**
   * パスワード確認
   */
  @NotBlank(message = "パスワード確認は必須です")
  private String passwordConfirm;
}
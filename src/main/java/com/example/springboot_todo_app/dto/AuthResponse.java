package com.example.springboot_todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 認証レスポンスDTO
 * ログイン成功時に返却される情報を保持します
 */
@Data
@AllArgsConstructor
public class AuthResponse {
  /**
   * ユーザーID
   */
  private Long userId;

  /**
   * ユーザー名
   */
  private String name;

  /**
   * メールアドレス
   */
  private String email;

  /**
   * リメンバートークン
   * rememberMeがtrueの場合のみ設定されます
   */
  private String rememberToken;
}
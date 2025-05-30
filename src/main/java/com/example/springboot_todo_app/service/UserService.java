package com.example.springboot_todo_app.service;

import com.example.springboot_todo_app.entity.User;
import java.util.Optional;

/**
 * ユーザー認証に関するサービスのインターフェース
 * ユーザーの登録、認証、ログイン状態の管理などの操作を定義します
 */
public interface UserService {
  /**
   * 新しいユーザーを登録します
   *
   * @param name            ユーザー名
   * @param email           メールアドレス
   * @param password        パスワード
   * @param passwordConfirm パスワード確認
   * @return 登録されたユーザー
   * @throws IllegalArgumentException パスワードが一致しない場合、またはメールアドレスが既に使用されている場合
   */
  User registerUser(String name, String email, String password, String passwordConfirm);

  /**
   * メールアドレスとパスワードでユーザーを認証します
   *
   * @param email    メールアドレス
   * @param password パスワード
   * @return 認証されたユーザー（認証に失敗した場合は空）
   */
  Optional<User> authenticateUser(String email, String password);

  /**
   * リメンバートークンを生成し、ユーザーに設定します
   *
   * @param user 対象のユーザー
   * @return 生成されたリメンバートークン
   */
  String generateRememberToken(User user);

  /**
   * リメンバートークンでユーザーを認証します
   *
   * @param rememberToken リメンバートークン
   * @return 認証されたユーザー（認証に失敗した場合は空）
   */
  Optional<User> authenticateByRememberToken(String rememberToken);

  /**
   * ユーザーのリメンバートークンを無効化します
   *
   * @param user 対象のユーザー
   */
  void invalidateRememberToken(User user);
}
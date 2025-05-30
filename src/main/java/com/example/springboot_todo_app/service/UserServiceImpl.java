package com.example.springboot_todo_app.service;

import com.example.springboot_todo_app.entity.User;
import com.example.springboot_todo_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * UserServiceの実装クラス
 * ユーザーの登録、認証、ログイン状態の管理などの操作を提供します
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /**
   * {@inheritDoc}
   * 新しいユーザーを登録します。
   * パスワードの一致確認とメールアドレスの重複チェックを行います。
   */
  @Override
  public User registerUser(String name, String email, String password, String passwordConfirm) {
    // パスワードの一致確認
    if (!password.equals(passwordConfirm)) {
      throw new IllegalArgumentException("パスワードが一致しません");
    }

    // メールアドレスの重複チェック
    if (userRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("このメールアドレスは既に使用されています");
    }

    // ユーザーの作成
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setPasswordConfirm(passwordEncoder.encode(passwordConfirm));

    return userRepository.save(user);
  }

  /**
   * {@inheritDoc}
   * メールアドレスとパスワードでユーザーを認証します。
   */
  @Override
  public Optional<User> authenticateUser(String email, String password) {
    return userRepository.findByEmail(email)
        .filter(user -> passwordEncoder.matches(password, user.getPassword()));
  }

  /**
   * {@inheritDoc}
   * リメンバートークンを生成し、ユーザーに設定します。
   */
  @Override
  public String generateRememberToken(User user) {
    String token = UUID.randomUUID().toString();

    user.setRememberToken(token);
    user.setRememberTokenExpiresAt(LocalDateTime.now().plusDays(30)); // 30日間有効
    userRepository.save(user);
    return token;
  }

  /**
   * {@inheritDoc}
   * リメンバートークンでユーザーを認証します。
   */
  @Override
  public Optional<User> authenticateByRememberToken(String rememberToken) {
    return userRepository.findByRememberToken(rememberToken)
        .filter(user -> {
          LocalDateTime expiresAt = user.getRememberTokenExpiresAt();
          return expiresAt != null && expiresAt.isAfter(LocalDateTime.now());
        });
  }

  /**
   * {@inheritDoc}
   * ユーザーのリメンバートークンを無効化します。
   */
  @Override
  public void invalidateRememberToken(User user) {
    user.setRememberToken(null);
    user.setRememberTokenExpiresAt(null);
    userRepository.save(user);
  }
}
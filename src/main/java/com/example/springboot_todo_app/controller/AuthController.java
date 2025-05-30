package com.example.springboot_todo_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.example.springboot_todo_app.service.UserService;
import com.example.springboot_todo_app.dto.UserRegisterRequest;
import com.example.springboot_todo_app.dto.UserLoginRequest;
import com.example.springboot_todo_app.dto.AuthResponse;
import com.example.springboot_todo_app.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 認証に関するコントローラー
 * ユーザー登録、ログイン、ログアウトなどの操作を提供します
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Validated
public class AuthController {

  private final UserService userService;

  /**
   * ユーザー登録エンドポイント
   * 新しいユーザーを登録します
   *
   * @param request ユーザー登録情報
   * @return 認証レスポンス（ユーザー情報）
   */
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerUser(@Validated @RequestBody UserRegisterRequest request) {
    userService.registerUser(
        request.getName(),
        request.getEmail(),
        request.getPassword(),
        request.getPasswordConfirm());
  }

  /**
   * ログインエンドポイント
   * メールアドレスとパスワードで認証を行います
   *
   * @param request ログイン情報
   * @return 認証レスポンス（ユーザー情報とリメンバートークン）
   */
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public AuthResponse loginUser(
      @Validated @RequestBody UserLoginRequest request,
      HttpServletRequest httpRequest,
      HttpServletResponse response) {
    User user = userService.authenticateUser(request.getEmail(), request.getPassword())
        .orElseThrow(() -> {
          return new IllegalArgumentException("メールアドレスまたはパスワードが正しくありません");
        });

    String rememberToken = userService.generateRememberToken(user);

    // if (request.isRememberMe()) {
    // rememberToken = userService.generateRememberToken(user);
    // }

    return new AuthResponse(user.getId(), user.getName(), user.getEmail(), rememberToken);
  }

  /**
   * ログアウトエンドポイント
   * リメンバートークンを無効化します
   *
   * @param userId ユーザーID
   */
  @PostMapping("/logout")
  @ResponseStatus(HttpStatus.OK)
  public void logoutUser(
      @RequestParam String userId,
      HttpServletResponse response) {
    User user = userService.authenticateByRememberToken(userId)
        .orElseThrow(() -> {
          log.error("User not found for logout: {}", userId);
          return new IllegalArgumentException("ユーザーが見つかりません");
        });
    userService.invalidateRememberToken(user);
  }
}
package com.example.springboot_todo_app.repository;

import com.example.springboot_todo_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * ユーザー情報のデータアクセスを提供するリポジトリインターフェース
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
  /**
   * メールアドレスでユーザーを検索します
   *
   * @param email 検索するメールアドレス
   * @return 該当するユーザー（存在しない場合は空）
   */
  Optional<User> findByEmail(String email);

  /**
   * メールアドレスが既に存在するか確認します
   *
   * @param email 確認するメールアドレス
   * @return 存在する場合はtrue
   */
  boolean existsByEmail(String email);

  /**
   * リメンバートークンでユーザーを検索します
   *
   * @param rememberToken 検索するリメンバートークン
   * @return 該当するユーザー（存在しない場合は空）
   */
  Optional<User> findByRememberToken(String rememberToken);
}
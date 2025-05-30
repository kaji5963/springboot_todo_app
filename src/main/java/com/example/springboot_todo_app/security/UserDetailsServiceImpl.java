package com.example.springboot_todo_app.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.springboot_todo_app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
    try {
      return userService.authenticateByRememberToken(token)
          .map(user -> {
            UserDetails userDetails = new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            return userDetails;
          })
          .orElseThrow(() -> {
            return new UsernameNotFoundException("User not found");
          });
    } catch (Exception e) {
      log.error("Error loading user by token: {}", token, e);
      throw e;
    } finally {
      log.debug("=== Completed loadUserByUsername ===");
    }
  }
}
package com.milind.sample.oauth.repository;

import com.milind.sample.oauth.domain.User;

public interface CustomUserRepository {

    public User getUserByUsername(String username);
}

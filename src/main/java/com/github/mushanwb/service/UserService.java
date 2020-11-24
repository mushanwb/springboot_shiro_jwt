package com.github.mushanwb.service;

import com.github.mushanwb.entity.User;
import com.github.mushanwb.entity.UserSource;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public User getUser(String username) {

        // 没有此用户直接返回null
        if (!UserSource.getData().containsKey(username)) {
            return null;
        }

        return UserSource.getData().get(username);
    }
}

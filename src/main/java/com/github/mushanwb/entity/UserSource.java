package com.github.mushanwb.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserSource {

    // 由于后面需要通过用户名判断用户存不存在，因此这里使用 map 结构好一点
    private static Map<String, User> data = new ConcurrentHashMap<>();

    static {
        User mockUser1 = User.mockUser("smith", "smith123");
        User mockUser2 = User.mockUser("danny", "danny123");

        data.put("smith", mockUser1);
        data.put("danny", mockUser2);
    }

    public static Map<String, User> getData() {
        return data;
    }
}

package com.github.mushanwb.entity;

import java.util.ArrayList;
import java.util.List;

public class UserSource {

    private static List<User> data = new ArrayList<>();

    static {
        User mockUser1 = User.mockUser("smith", "smith123");
        User mockUser2 = User.mockUser("danny", "danny123");

        data.add(mockUser1);
        data.add(mockUser2);
    }

    public static List<User> getData() {
        return data;
    }
}

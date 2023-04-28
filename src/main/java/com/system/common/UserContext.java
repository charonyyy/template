package com.system.common;

import com.system.entity.User;

public class UserContext {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        threadLocal.set(user);
    }

    public static String getUsername() {
        return threadLocal.get().getUsername();
    }

    public static void removeUser() {
        threadLocal.remove();
    }
}
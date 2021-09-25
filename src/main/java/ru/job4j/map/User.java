package ru.job4j.map;

import java.util.Calendar;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Vasya", 2, Calendar.getInstance());
        User user2 = new User("Vasya", 2, Calendar.getInstance());
        Map<User, Object> users = Map.of(user1, new Object(), user2, new Object());
        System.out.println(users);
    }
}

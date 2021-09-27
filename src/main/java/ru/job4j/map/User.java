package ru.job4j.map;

import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

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
        Calendar cal = new Calendar.Builder()
                .setDate(1977, 10, 2)
                .setTimeOfDay(14, 45, 11, 55)
                .build();
        User user1 = new User("Vasya", 2, cal);
        User user2 = new User("Vasya", 2, cal);
        Map<User, Object> users = Map.of(user1, new Object(), user2, new Object());
        System.out.println(users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}

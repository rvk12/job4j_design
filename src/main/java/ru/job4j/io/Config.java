package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(s -> {
                        if (!s.trim().startsWith("#") && !s.isBlank()) {
                            String[] line = s.split("=", 2);
                            if (line.length <= 1 || line[0].isEmpty() || line[1].isEmpty()) {
                                throw new IllegalArgumentException("Отсутствует ключ или значение для строки: " + s);
                            }
                            values.put(line[0], line[1]);
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}
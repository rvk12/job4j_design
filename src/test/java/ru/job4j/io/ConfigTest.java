package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "src/main/java/resources/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres=1=4"));
    }

    @Test
    void whenPairWithCommentsAndEmptyLines() {
        String path = "src/main/java/resources/pair_with_comment_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate"), is("postgres"));
    }

    @Test
    void whenPairWithIllegalValues() {
        String path = "src/main/java/resources/pair_with_illegal_values.properties";
        Config config = new Config(path);
        IllegalArgumentException ie = assertThrows(IllegalArgumentException.class, config::load);
        assertThat(ie.getMessage(), containsString("Отсутствует ключ или значение"));
    }
}
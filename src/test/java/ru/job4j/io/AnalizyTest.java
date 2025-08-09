package ru.job4j.io;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnalizyTest {

    @Test
    public void unavailable(@TempDir Path tempDir) throws IOException {
        File target  = tempDir.resolve("target.csv").toFile();

        new Analizy().unavailable("src/main/java/resources/server.log", target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").isEqualTo(rsl.toString());
    }



}
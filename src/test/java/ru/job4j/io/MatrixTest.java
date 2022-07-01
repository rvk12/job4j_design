package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixTest {

    /**
     * Test multiplication 2 on 2
     */
    @Test
    public void when2on2() {
        String fileName = "result.txt";
        String expect = String.format("1 2%s2 4", System.lineSeparator());
        String actual = null;
        Matrix matrix = new Matrix();
        matrix.multiply(2, fileName);
        try {
            actual = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(actual, is(expect));
    }

    /**
     * Test multiplication 3 on 3
     */
    @Test
    public void when3on3() {
        String fileName = "result.txt";
        String expect = String.format("1 2 3%s2 4 6%s3 6 9", System.lineSeparator(), System.lineSeparator());
        String actual = null;
        Matrix matrix = new Matrix();
        matrix.multiply(3, fileName);
        try {
            actual = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(actual, is(expect));
    }

}
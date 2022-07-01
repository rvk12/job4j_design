package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * @author rvk12
 * @version $Id$
 * @since 0.1
 */
public class Matrix {

    public void multiply(int size, String fileName) {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.write(String.valueOf((i + 1) * (j + 1)).getBytes());
                    if (j != size - 1) {
                        out.write(" ".getBytes());
                    }
                }
                if (i != size - 1) {
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
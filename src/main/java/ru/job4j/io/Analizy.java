package ru.job4j.io;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Analizy {
    public static void main(String[] args) {
        new Analizy().unavailable("src/main/java/resources/server.log", "src/main/java/resources/unavailable.csv");
        new Analizy().unavailable("src/main/java/resources/server2.log", "src/main/java/resources/unavailable2.csv");
    }

    public void unavailable(String source, String target) {
        Map<String, Boolean> values = new LinkedHashMap<>();
        boolean available = true;
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {

            String line = read.readLine();
            while (line != null) {
                if ((line.startsWith("400") || line.startsWith("500")) && available) {
                    String val = line.split("(400|500)")[1];
                    available = false;
                    values.put(val.trim(), available);
                } else if ((line.trim().startsWith("200") || line.trim().startsWith("300")) && !available) {
                    String val = line.split("(200|300)")[1];
                    available = true;
                    values.put(val.trim(), available);
                }
                line = read.readLine();
            }
            for (Map.Entry<String, Boolean> e : values.entrySet()) {
                if (!e.getValue()) {
                    out.print(e.getKey() + ";");
                } else {
                    out.println(e.getKey() + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
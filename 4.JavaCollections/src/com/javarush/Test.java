package com.javarush;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(
                Paths.get("~/Dropbox/Development/Java/JavaRush/JavaRushTasks/out.log")));

        System.out.println(content);
    }
}

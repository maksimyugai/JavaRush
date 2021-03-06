package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt",
                Paths.get("~/Dropbox/Development/Java/JavaRush/JavaRushTasks/"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        InputStream is = url.openStream();
        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);
        Path tempFile = Files.createTempFile("temp-", ".temp");
        Files.copy(is, tempFile);

        return Files.move(tempFile, downloadDirectory.resolve(Paths.get(fileName)));
    }
}

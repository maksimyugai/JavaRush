package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        File file = Paths.get(args[0]).toFile();
        int pos = Integer.valueOf(args[1]);
        String text = args[2];
        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        byte[] bytes = text.getBytes();

        if (pos > raf.length()) {
            raf.seek(raf.length());
            raf.write(bytes);
        } else {
            raf.seek(pos);
            raf.write(bytes);
        }
    }
}

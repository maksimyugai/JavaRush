package com.javarush.task.task32.task3210;

/*
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        byte[] bytes = new byte[text.length()];

        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        raf.seek(number);
        raf.read(bytes, 0, text.length());

        String newString = new String(bytes);

        raf.seek(raf.length());
        if (text.equals(newString)) {
            raf.write("true".getBytes());
        } else {
            raf.write("false".getBytes());
        }
    }
}

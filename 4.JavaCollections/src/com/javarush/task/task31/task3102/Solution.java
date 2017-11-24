package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/* 
Находим все файлы

Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используй очередь, рекурсию не используй.
Верни список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.


Требования:
1. Метод getFileTree должен принимать аргументом String root, по которому нужно найти все вложенные файлы.
2. Метод getFileTree должен возвращать список строк.
3. Нужно реализовать метод getFileTree: найти все файлы по указанному пути и добавить их в список.
4. Метод getFileTree должен быть вызван только 1 раз (рекурсию не использовать).
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new LinkedList<>();
        File rootDir = new File(root);

        for (File f : rootDir.listFiles()) {
            if (f.isDirectory()) {
                String[] listOfFiles = f.list();
                String s = f.getAbsolutePath();
            }

            if (f.isFile()) list.add(f.getParent());
//            System.out.println(f.getName());
        }

        return list;

    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFileTree("/home/maksimyugai/"));
//        getFileTree("/home/maksimyugai/");
    }
}

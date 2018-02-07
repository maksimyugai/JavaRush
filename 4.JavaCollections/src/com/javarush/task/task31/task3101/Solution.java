package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {

        TreeSet<File> lower50 = new TreeSet<>();

        File path = new File(args[0]);

        File resultFileAbsolutePath = new File(args[1]);

        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try (FileOutputStream out = new FileOutputStream(allFilesContent)) {

            Stack<File> stack = new Stack<>();
            stack.push(path);
            while (!stack.empty()) {
                File elt = stack.pop();
                if (elt.isDirectory()) {
                    for (File file1 : elt.listFiles()) { //pushStack
                        stack.push(file1);
                    }
                }
                if (elt.isFile()) {
                    if (elt.length() > 50) {
                        FileUtils.deleteFile(elt);
                    } else {
                        lower50.add(elt);
                    }
                }
            }

            TreeMap<String, File> fileAndPath = new TreeMap<>();
            for(File f : lower50){
                fileAndPath.put(f.getName(), f);
            }
            for(Map.Entry<String, File> pair : fileAndPath.entrySet()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pair.getValue()));
                String s;
                while ((s = bufferedReader.readLine()) != null){
                    out.write((s+"\n").getBytes());
                }
                bufferedReader.close();
            }
            out.close();
        } catch (IOException e) {
        }
    }
}

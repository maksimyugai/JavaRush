package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        try {
            firstFileName = new BufferedReader(new InputStreamReader(System.in)).readLine();
            secondFileName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements ReadFileInterface {
        FileReader file;

        @Override
        public void run() {
            try {
                file = new FileReader("JavaRushPlugin.properties");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void setFileName(String fullFileName) {

        }

        @Override
        public String getFileContent() {
            StringBuilder content = new StringBuilder();

            try {
                while (file.ready()) {
                    content.append((char) file.read());
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }

            return content.toString();
        }
    }
}

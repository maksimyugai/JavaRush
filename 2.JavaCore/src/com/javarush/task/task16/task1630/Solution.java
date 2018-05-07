package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            firstFileName = bf.readLine();
            secondFileName = bf.readLine();
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
        f.join();
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
        String content = "";
        BufferedReader buff;

        @Override
        public void setFileName(String fullFileName) {
            try {
                buff = new BufferedReader(new FileReader(fullFileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        @Override
        public String getFileContent() {
            return content.trim();
        }

        @Override
        public void run() {
            try {
                while (buff.ready()) {
                    content = content + " " + buff.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.javarush.task.task16.task1630;

/*
* Требования:
1. Статический блок класса Solution должен считывать с консоли имена двух файлов и сохранять их в переменные firstFileName и secondFileName.
2. Объяви в классе Solution public static класс ReadFileThread.
3. Класс ReadFileThread должен реализовывать интерфейс ReadFileInterface.
4. Класс ReadFileThread должен быть унаследован от подходящего класса.
5. Метод run класса ReadFileThread должен считывать строки из файла, установленного методом setFileName.
А метод getFileContent, этого же класса, должен возвращать вычитанный контент.
Возвращаемое значение - это одна строка, состоящая из строк файла, разделенных пробелами.
6. Метод systemOutPrintln должен вызывать метод join у созданного объекта f.
7. Вывод программы должен состоять из 2х строк. Каждая строка - содержимое одного файла.

/home/maksimyugai/Dropbox/Development/Java/test/sometest.txt
/home/maksimyugai/Dropbox/Development/Java/test/sometest2.txt
*/

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        try {
            firstFileName = String.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
            secondFileName = String.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine());
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

    //3. Внутри класса Solution создай нить public static ReadFileThread, которая реализует
    //   интерфейс ReadFileInterface (Подумай, что больше подходит — Thread или Runnable).
    public static class ReadFileThread extends Thread implements ReadFileInterface {
        String fullFileName = "";
        String fileContent = "";

        //3.1. Метод setFileName должен устанавливать имя файла, из которого будет читаться содержимое.
        @Override
        public void setFileName(String fullFileName) {
            this.fullFileName = fullFileName;
        }

        //3.2. Метод getFileContent должен возвращать содержимое файла.
        @Override
        public String getFileContent() {
            return fileContent.trim();
        }
/*

        3.3. В методе run считай содержимое файла, закрой поток. Раздели пробелом строки файла.
        5.  Метод run класса ReadFileThread должен считывать строки из файла, установленного методом setFileName.
            А метод getFileContent, этого же класса, должен возвращать вычитанный контент.
            Возвращаемое значение - это одна строка, состоящая из строк файла, разделенных пробелами.
*/
        @Override
        public void run() {
            try (BufferedReader bf = new BufferedReader(new FileReader(this.fullFileName))) {
                while (bf.ready()) {
                    this.fileContent += bf.readLine() + " ";
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

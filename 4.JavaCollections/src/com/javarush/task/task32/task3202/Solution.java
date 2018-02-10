package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока

Реализуй логику метода getAllDataFromInputStream. Он должен вернуть StringWriter,
содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.

Требования:
1. Публичный статический метод getAllDataFromInputStream (InputStream) должен существовать.
2. Метод getAllDataFromInputStream (InputStream) должен возвращать StringWriter.
3. Метод getAllDataFromInputStream (InputStream) должен вернуть StringWriter,
    который содержит все данные из переданного потока.
4. Возвращаемый объект ни при каких условиях не должен быть null.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();
        if (is != null) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            StringBuilder result = new StringBuilder();

            while (bf.ready()) {
                result.append(bf.readLine());
            }

            stringWriter.write(result.toString());
            return stringWriter;
        } else return stringWriter;
    }
}
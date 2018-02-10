package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря

Для тебя не составит труда реализовать шифр Цезаря, напомню что это просто сдвиг вправо по алфавиту на key букв.

Реализуй логику метода String decode(StringReader reader, int key).
Метод получает данные в закодированном виде.
Он должен вернуть дешифрованную строку, что хранится в StringReader - е.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0

    }

    public static String decode(StringReader reader, int key) throws IOException {
        String result = "";
        if (reader == null) return result;

        BufferedReader bf = new BufferedReader(reader);
        char[] chars = bf.readLine().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] += key;
        }

        result = new String(chars);
        return result;
    }
}

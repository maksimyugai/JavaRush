package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {

        String file = args[0];

        try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
            while(bf.ready()) {
                String[] line = bf.readLine().split(" ");
                StringBuilder name = new StringBuilder();

                for (int i = 0; i < line.length - 3; i++) {
                    name.append(line[i]);
                    name.append(" ");
                }

                int day = Integer.parseInt(line[line.length - 3]);
                int month = Integer.parseInt(line[line.length - 2]);
                int year = Integer.parseInt(line[line.length - 1]);

                Date date = new GregorianCalendar(year, month-1, day).getTime();

                PEOPLE.add(new Person(name.toString().trim(), date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //for (Person p: PEOPLE) {
        //    System.out.println(p.getName() + " " + p.getBirthday());
        //}
    }
}

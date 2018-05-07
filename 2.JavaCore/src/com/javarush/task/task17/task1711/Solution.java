package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут

        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i <= args.length - 1; i++)  {
                        String name = args[i];
                        Sex sex = (args[++i].equals("м") ? Sex.MALE : Sex.FEMALE);
                        Date d = convertDate(args[++i]);

                        if (sex.equals(Sex.FEMALE)) allPeople.add(Person.createFemale(name, d));
                        else if (sex.equals(Sex.MALE)) allPeople.add(Person.createMale(name, d));
                        System.out.println(allPeople.size() - 1);
                    }
                }

                break;

            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i <= args.length - 1; i++) {
                        int id = Integer.parseInt(args[i]);
                        Person p = allPeople.get(id);

                        p.setName(args[++i]);
                        p.setSex(args[++i].equals("м") ? Sex.MALE : Sex.FEMALE);
                        p.setBirthDay(convertDate(args[++i]));
                    }
                }

                break;

            case "-d":
                synchronized (allPeople) {
                    Person p;
                    if (args.length > 2) {
                        for (int i = 1; i <= args.length - 1; i++) {
                            p = allPeople.get(Integer.parseInt(args[i]));
                            p.setName(null);
                            p.setSex(null);
                            p.setBirthDay(null);
                        }
                    } else {
                        p = allPeople.get(Integer.parseInt(args[1]));
                        p.setName(null);
                        p.setSex(null);
                        p.setBirthDay(null);
                    }
                }

                break;

            case "-i":
                synchronized (allPeople) {
                    Person pp;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                    if (args.length > 2) {
                        for (int i = 1; i <= args.length - 1; i++) {
                            pp = allPeople.get(Integer.parseInt(args[i]));
                            System.out.println(pp.getName() + " " + (pp.getSex().equals(Sex.FEMALE) ? "ж" : "м") + " "
                                    + simpleDateFormat.format(pp.getBirthDay()));
                        }
                    } else {
                        pp = allPeople.get(Integer.parseInt(args[1]));
                        System.out.println(pp.getName() + " " + (pp.getSex().equals(Sex.FEMALE) ? "ж" : "м") + " "
                                + simpleDateFormat.format(pp.getBirthDay()));
                    }
                }
                break;
        }

        //System.out.println(allPeople.size());

        //for (Person p : allPeople) {
        //    System.out.println(p.getName());
        //}
    }

    private static Date convertDate(String string) {
        int day = Integer.parseInt(string.split("/")[0]);
        int month = Integer.parseInt(string.split("/")[1]);
        int year = Integer.parseInt(string.split("/")[2]);

        return new GregorianCalendar(year, month-1, day).getTime();
    }
}

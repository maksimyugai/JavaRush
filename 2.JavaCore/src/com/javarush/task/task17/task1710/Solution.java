package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        if (args.length == 0) {
            System.exit(1);
        }

        switch (args[0]) {
            case "-c":
                if (args[2].equals("м")) {
                    allPeople.add(Person.createMale(args[1], Solution.convertDate(args[3])));
                    System.out.println(Solution.allPeople.size() - 1);
                } else if (args[2].equals("ж")) {
                    allPeople.add(Person.createFemale(args[1], Solution.convertDate(args[3])));
                    System.out.println(Solution.allPeople.size() - 1);
                }

                break;

            case "-u":
                Person p = allPeople.get(Integer.parseInt(args[1]));
                p.setName(args[2]);
                p.setSex(args[3].equals("м") ? Sex.MALE : Sex.FEMALE);
                p.setBirthDay(Solution.convertDate(args[4]));

                break;

            case "-d":
                //allPeople.remove(allPeople.get(Integer.parseInt(args[1])));
                Person pp = allPeople.get(Integer.parseInt(args[1]));
                pp.setName(null);
                pp.setSex(null);
                pp.setBirthDay(null);

                break;

            case "-i":
                String name = allPeople.get(Integer.parseInt(args[1])).getName();
                String sex = allPeople.get(Integer.parseInt(args[1])).getSex().toString().equals("MALE") ? "м": "ж";
                Date date = allPeople.get(Integer.parseInt(args[1])).getBirthDay();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                System.out.println(name + " " + sex + " " + simpleDateFormat.format(date));
        }

        //for (Person p: allPeople) {
        //    System.out.println(p.getName() + " " + p.getSex() + " " + p.getBirthDay());
        //}
    }

    private static Date convertDate(String string) {
        String[] db = string.split("/");
        int day = Integer.parseInt(db[0]);
        int month = Integer.parseInt(db[1]);
        int year = Integer.parseInt(db[2]);

        return new GregorianCalendar(year, month-1, day).getTime();
    }
}

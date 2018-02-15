package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        logger.debug("Values initialization value1 = %s \n" +
                "value2 = %s \n" +
                "value3 = %s", value1, value2, value3);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static void main(String[] args) {

    }

    public void calculateAndSetValue3(long value) {
        logger.trace("Calculate and set value3");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("Change value1 = %s", value1);
        } else {
            value1 = (int) value;
            logger.debug("Change value1 = %s", value1);
        }
    }

    public void printString() {
        if (value2 != null) {
            logger.trace("Print value2");
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        if (value3 != null) {
            logger.trace("Print value3");
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        try {
            logger.trace("Divide %d to %d", number1, number2);
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error(e.toString());
        }
    }

    public void setValue1(int value1) {
        this.value1 = value1;
        logger.debug("Change value1 = %s to %s", this.value1, value1);
    }

    public void setValue2(String value2) {
        this.value2 = value2;
        logger.debug("Change value2 = %s to %s", this.value2, value2);
    }

    public void setValue3(Date value3) {
        this.value3 = value3;
        logger.debug("Change value3 = %s to %s", this.value3, value3);
    }
}

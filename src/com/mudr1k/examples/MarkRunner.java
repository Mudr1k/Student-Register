package com.mudr1k.examples;
/**
 * 1. получить среднюю оценку по предмету. На вход предмет строка - на выход оценка дабл
 * 2. получить среднюю оценку студента по предмету. На вход предмет и студент - на выход оценка. Не обсчитывать второй раз
 * взять из кеша первого задания. Если раньше запрашивали то повторные запросы мгновенно
 * 3.* Если после того как запрашивали, проверить не устаревший ли кеш, так как могли добавляться оценки после последнего запроса
 */

import java.util.Arrays;
import java.util.List;

public class MarkRunner {

    public static void main(String[] args) {

        List<Mark> marks = Arrays.asList(
                new Mark("Math", "Shevchenko", 5),
                new Mark("Math", "Shevchenko", 4),
                new Mark("Physics", "Shevchenko", 5),
                new Mark("Biology", "Shevchenko", 5),
                new Mark("Math", "Mudrevsky", 3),
                new Mark("Biology", "Mudrevsky", 3),
                new Mark("Physics", "Ivanov", 2),
                new Mark("Physics", "Ivanov", 4),
                new Mark("Biology", "Ivanov", 5)
        );

        Register register = new Register(marks);
        register.print();
        System.out.println("\n");

        double math = register.countAverageMark("Math");
        System.out.println("math = " + math);

        double biology = register.countAverageMark("Biology");
        System.out.println("biology = " + biology);

        double physics = register.countAverageMark("Physics");
        System.out.println("physics = " + physics);

        double labor = register.countAverageMark("Labor");
        System.out.println("labor = " + labor);

        System.out.println("AverageMark(\"Math\", \"Shevchenko\") = " +
                register.countAverageMark("Math", "Shevchenko"));

        register.add(new Mark("Math", "Shevchenko", 5));
        register.add(new Mark("Math", "Shevchenko", 5));
        register.add(new Mark("Math", "Shevchenko", 5));
        register.add(new Mark("Math", "Shevchenko", 5));
        register.add(new Mark("Math", "Shevchenko", 5));

        System.out.println("AverageMark(\"Math\", \"Shevchenko\") = " +
                register.countAverageMark("Math", "Shevchenko"));

        register.print();
        System.out.println("\n");

    }
}

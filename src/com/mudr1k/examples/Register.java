package com.mudr1k.examples;

import java.util.*;

public class Register {

    private List<Mark> register;
    private HashMap<String, HashMap<String, AverageMark>> mapRegister = null;

    public Register(List<Mark> register) {
        this.register = new ArrayList<>(register);
    }

    public void print() {
        sort();
        Mark prev = null;
        for (Mark mark : register) {
            if (prev != null && prev.compareTo(mark) == 0) {
                System.out.print(", " + mark.getMark());
            } else {
                System.out.print("\n" + mark.getSubject() + " " + mark.getStudent() + " " + mark.getMark());
            }
            prev = mark;
        }
    }

    public double countAverageMark(String subject) {
        buildMapRegister();
        int counter = 0;
        int sum = 0;

        HashMap<String, AverageMark> map = mapRegister.get(subject);
        if (map == null) {
            System.out.println("нет данных по предмету " + subject);
            return 0.0;
        }

        for (AverageMark value : map.values()) {
            sum += value.getMarkSum();
            counter += value.getCounter();
        }

        double average = sum / (double) counter;

        return round(average);
    }

    public double countAverageMark(String subject, String student) {
        buildMapRegister();

        HashMap<String, AverageMark> map = mapRegister.get(subject);
        if (map == null) {
            System.out.println("нет данных по предмету " + subject);
            return 0.0;
        }

        AverageMark avMark = map.get(student);
        if (avMark == null) {
            System.out.println("нет данных об оценках студента " + student + " по предмету " + subject);
            return 0.0;
        }

        return round(avMark.getAverage());
    }

    private double round(double a) {
        return Math.round(a * 10.0) / 10.0;
    }

    private void buildMapRegister() {
        if (mapRegister != null) {
            return;
        }
        mapRegister = new HashMap<>();

        for (Mark mark : register) {
            addMapRegister(mark);
        }
    }

    private void addMapRegister(Mark mark) {
        if (mapRegister == null) {
            return;
        }

        String sub = mark.getSubject();
        String st = mark.getStudent();
        int m = mark.getMark();

        HashMap<String, AverageMark> stM = mapRegister.get(sub);

        if (stM == null) {
            stM = new HashMap<>();
            AverageMark avMark = new AverageMark(m);
            stM.put(st, avMark);
            mapRegister.put(sub, stM);
        } else {
            var marks = stM.get(st);
            if (marks == null) {
                AverageMark avMark = new AverageMark(m);
                stM.put(st, avMark);
            } else {
                marks.addMark(m);
            }
        }
    }

    public void add(Mark mark) {
        register.add(mark);
        addMapRegister(mark);
    }

    private void sort() {
        Collections.sort(register);
    }
}

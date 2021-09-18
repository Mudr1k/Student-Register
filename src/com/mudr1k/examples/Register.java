package com.mudr1k.examples;

import java.util.*;

public class Register {
//    private ArrayList<Mark> register;

//    public Register(ArrayList<Mark> register) {
//        this.register = register;
//    }


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

//    private void buildMapRegister() {
//        if (isActualMapRegister) {
//            return;
//        }
//
//        for (Mark mark : register) {
//            String sub = mark.getSubject();
//            String st = mark.getStudent();
//            int m = mark.getMark();
//
//            HashMap<String, ArrayList<Integer>> stM = mapRegister.get(sub);
//            ArrayList<Integer> list;
//
//            if (stM == null) {
//                stM = new HashMap<>();
//                list = new ArrayList<>();
//                list.add(m);
//                stM.put(st, list);
//                mapRegister.put(sub, stM);
//            } else {
//                var marks = stM.get(st);
//                if (marks == null) {
//                    list = new ArrayList<>();
//                    list.add(m);
//                    stM.put(st, list);
//                } else {
//                    marks.add(m);
//                }
//            }
//        }
//        setActualMapRegister(true);

//    }

    private void sort() {
        Collections.sort(register);
    }


//    public void convertToArrayList() {
//        ArrayList<String> list = new ArrayList<>();
//        for (Mark mark : register) {
//            list.add("" + mark.getSubject() + "," + mark.getStudent() + "," + mark.getMark());
//        }
//        for (String s : list) {
//            System.out.println(s);
//        }
//        System.out.println("\n");
//
//        int reduce = list.stream()
//                .filter(str -> str.startsWith("Math"))
//                .filter(str -> str.contains("Shevchenko"))
//                .map(str -> str.substring(str.length() - 1))
//                .map(Integer::valueOf)
//                .reduce(0, Integer::sum);
//
//
//        System.out.println(reduce);
//
////                .forEach(System.out::println);
//
//
//
//    }

}

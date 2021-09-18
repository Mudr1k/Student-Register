package com.mudr1k.examples;

public class AverageMark {
    private int markSum = 0;
    private int counter = 0;

    public AverageMark(int mark) {
        addMark(mark);
    }

    public void addMark(int mark) {
        markSum += mark;
        ++counter;
    }

    public double getAverage() {
        return markSum / (double) counter;
    }

    public int getCounter() {
        return counter;
    }

    public int getMarkSum() {
        return markSum;
    }
}

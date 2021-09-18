package com.mudr1k.examples;

public class Mark implements Comparable<Mark> {
    private String subject;
    private String student;
    private int mark;

    public Mark(String subject, String student, int mark) {
        this.subject = subject;
        this.student = student;
        this.mark = mark;
    }

    public String getSubject() {
        return subject;
    }

    public String getStudent() {
        return student;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public int compareTo(Mark m) {
        int ret = this.subject.compareTo(m.subject);
        if (ret == 0) {
            return this.student.compareTo(m.student);
        }
        return ret;
    }

}

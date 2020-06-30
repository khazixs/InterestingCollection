package com.ic.learn.concurrent.useDelayQueue;

public class StudentTask implements Task {

    private Integer student;

    public StudentTask(Integer student) {
        this.student = student;
    }

    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    @Override
    public void executeTask() {
        System.out.println("学生任务执行" + student);
    }
}

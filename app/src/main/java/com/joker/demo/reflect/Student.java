package com.joker.demo.reflect;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Target;

class Student {
    private String name;

    public String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getScore() {
        return score;
    }

    private String getScore(String  name) {
        return score;
    }

    private void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

}

package com.isystem.tests;

import android.widget.TextView;

public class UserData {
    private String name;
    private int trueAnswers;
    private int falseAnswers;

    public UserData(String name, int trueAnswers, int falseAnswers) {
        this.name = name;
        this.trueAnswers = trueAnswers;
        this.falseAnswers = falseAnswers;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrueAnswers() {
        return trueAnswers;
    }

    public void setTrueAnswers(int trueAnswers) {
        this.trueAnswers = trueAnswers;
    }

    public int getFalseAnswers() {
        return falseAnswers;
    }

    public void setFalseAnswers(int falseAnswers) {
        this.falseAnswers = falseAnswers;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", trueAnswers=" + trueAnswers +
                ", falseAnswers=" + falseAnswers +
                '}';
    }
}

package com.isystem.tests;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionManager{
    private ArrayList<QuestionData> data;
    private int currentPositions=0;
    private int totalQuestion=0;
    private int totalTrue;
    private int totalFalse;

    public QuestionManager() {
    }

    public QuestionManager(ArrayList<QuestionData> data) {
        this.data = data;
        Collections.shuffle(data);
        totalQuestion=data.size();
    }

    public void setData(ArrayList<QuestionData> data){
        this.data = data;
        Collections.shuffle(data);
        totalQuestion=data.size();
    }

    private QuestionData getCurrentQuestion(){
        return data.get(currentPositions);
    }
    public String getQuestion(){
        return getCurrentQuestion().getQuestion();
    }
    public String getVariantA(){
        return getCurrentQuestion().getAnswerA();
    }
    public String getVariantB(){
        return getCurrentQuestion().getAnswerB();
    }
     public String getVariantC(){
        return getCurrentQuestion().getAnswerC();
    }
    public String getVariantD(){
        return getCurrentQuestion().getAnswerD();
    }
    public boolean checkAnswer(String answer){
        String text=answer;
        boolean isTrue=false;
        if(answer.equalsIgnoreCase(getCurrentQuestion().getAnswer())){
            totalTrue++;
            isTrue=true;
        }else{
            totalFalse++;
            isTrue=false;
        }
        if(hasQuestion()){
            currentPositions++;
        }
        return isTrue;
    }
    public boolean isFinish(){
        return currentPositions == totalQuestion;
    }
    public boolean hasQuestion(){
        return currentPositions< totalQuestion;
    }
    public int getCurrentLevel(){
        return currentPositions+1;
    }
    public int getTotal(){
        return data.size();
    }
}

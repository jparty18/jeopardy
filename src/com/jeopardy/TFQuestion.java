package com.jeopardy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TFQuestion extends Question {
    private int currentAnswerIndex;

    public TFQuestion(int session, String questionContent, int dollarValue, boolean answer) {
        super(session, questionContent, dollarValue, answer);
    }

    public void showAnswerChoices(List<String> answers) {
        List<String> choices = new ArrayList<>();
        String answer = this.getAnswer();

        int index = new Random().nextInt(2);
        int count = 1;
        for (String a : answers) {
            // only accept true or false answer values
            if(!"True".equals(a) && !"False".equals(a)){
                continue;
            }
            System.out.print(count + ": " + a + "\t" + "\t");
            if (a.equals(answer)) {
                super.currentAnswerIndex = count;
            }
            count ++;
        }
        System.out.print(0 + ": Ask for help. ");
        System.out.print("\n" + "Your answer: ");
    }
}
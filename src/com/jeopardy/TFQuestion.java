package com.jeopardy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TFQuestion extends Question {
    public TFQuestion(int session, String questionContent, int dollarValue, boolean answer) {
        super(session, questionContent, dollarValue, answer);
        List<String> temp = new ArrayList<>();

        temp.add("True");
        temp.add("False");
        temp.add("It depends");
        super.setAnswers(temp);
    }

    public List<String> showAnswerChoices() {
        List<String> choices = new ArrayList<>();

        int count = 1;
        for (String answer : getAnswers()) {
            choices.add(count + ": " + answer + "\t" + "\t");
            if (answer.equals(getAnswer())) {
                super.currentAnswerIndex = count;
            }
            count++;
        }
        choices.add(Board.HELP_INPUT + ": Ask for help. ");
        choices.add("\n" + "Your answer: ");

        return choices;
    }
}
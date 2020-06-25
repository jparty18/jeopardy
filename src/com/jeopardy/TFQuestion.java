package com.jeopardy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TFQuestion extends Question {
    public TFQuestion(int session, String questionContent, int dollarValue, boolean answer) {
        super(session, questionContent, dollarValue, answer);
    }

    public List<String> showAnswerChoices(List<String> answers) {
        List<String> choices = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        temp.add("True");
        temp.add("False");
        temp.add("It depends");

        int count = 1;
        for (String answer : temp) {
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
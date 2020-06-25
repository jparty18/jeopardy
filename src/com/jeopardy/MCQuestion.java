package com.jeopardy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MCQuestion extends Question {


    public MCQuestion(int session, String questionContent, int dollarValue, String answer) {
        super(session, questionContent, dollarValue, answer);
    }

    public MCQuestion(int session, String questionContent, int dollarValue, String answer, List<String> answers) {
        this(session, questionContent, dollarValue, answer);
        super.setAnswers(answers);
    }

    public List<String> showAnswerChoices() {
        List<String> choices = new ArrayList<>();
        String answer = this.getAnswer();

        int answerIndex = new Random().nextInt(4) + 1;
        int count = 1;

        while (count < 5) {
            String wrongAnswer = getAnswers().get(new Random().nextInt(getAnswers().size()));
            if (count == answerIndex) {
                choices.add(count + ": " + answer + "\t" + "\t");
                super.currentAnswerIndex = answerIndex;
                count ++;
            } else if (!wrongAnswer.equals(answer) && count != answerIndex) {
                choices.add(count + ": " + wrongAnswer + "\t" + "\t");
                count ++;
            }
        }

        choices.add(Board.HELP_INPUT + ": Ask for help.");
        choices.add("\n" + "Your answer: ");

        return choices;
    }
}
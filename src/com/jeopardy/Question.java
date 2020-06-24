package com.jeopardy;

import java.util.List;
import java.util.Objects;

public abstract class Question {
    private boolean isDailyDouble = false;
    private boolean isTrueFalse = false;
    private int session;
    private String questionContent;
    private String answer;
    private int dollarValue;
    int currentAnswerIndex;

    //ctor
    private Question (int session, String questionContent, int dollarValue) {
        setSession(session);
        setQuestionContent(questionContent);
        setDollarValue(dollarValue);
        setDailyDouble();
    }

    public Question(int session, String questionContent, int dollarValue, String answer) {
        this(session, questionContent, dollarValue);
        setAnswer(answer);
    }

    public Question(int session, String questionContent, int dollarValue, boolean answer) {
        this(session, questionContent, dollarValue);
        isTrueFalse = true;
        if (answer) {
            setAnswer("True");
        } else {
            setAnswer("False");
        }
    }

    //business methods
    public void displayQuestion(){
        StringBuilder questionDisplay = new StringBuilder("\n" + "For $" + getDollarValue() + ": ");

        if(isDailyDouble){
            questionDisplay.append("\n" + "\u001B[44m \n \u001B[41m \u001B[30m" +
                    "*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*  D-A-I-L-Y   D-O-U-B-L-E  *$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*" +
                    "\n\u001B[44m" + "\n\u001B[0m" +
                    "For $" + getDollarValue()*2 + ": " + questionContent);
        } else {
            questionDisplay.append(getQuestionContent());
        }
        System.out.println(questionDisplay.toString());
    }

    public abstract void showAnswerChoices(List<String> answers);
    public boolean checkAnswer(int answer) {
        boolean result = answer == currentAnswerIndex;

        System.out.print(result ? "Correct! " : "Hmm... I don't think so. ");
        System.out.println("\n");

        return result;
    };

    //Accessor methods
    public boolean isDailyDouble() { return isDailyDouble; }
    private void setDailyDouble() {
        if(Math.random() < 0.26){
            isDailyDouble = true;
        }
    }

    public int getSession() { return session; }
    private void setSession(int session) {
        this.session = session;
    }

    public String getQuestionContent() { return questionContent; }
    private void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswer() { return answer; }
    private void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getDollarValue() { return dollarValue; }

    private void setDollarValue(int dollarValue) {
        this.dollarValue = dollarValue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "isDailyDouble=" + isDailyDouble +
                ", session=" + session +
                ", questionContent='" + questionContent + '\'' +
                ", dollarValue=" + dollarValue +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Question question = (Question) other;
        return isDailyDouble == question.isDailyDouble &&
                session == question.session &&
                dollarValue == question.dollarValue &&
                Objects.equals(questionContent, question.questionContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDailyDouble, session, questionContent, dollarValue);
    }
}

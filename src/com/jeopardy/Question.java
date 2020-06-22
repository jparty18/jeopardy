package com.jeopardy;

import java.util.Objects;

public class Question {

    private boolean isSelected = false;
    private boolean isDailyDouble = false;
    private int session;
    private String questionContent;
    private int dollarValue;

    //ctor
    public Question(int session, String questionContent, int dollarValue) {
        setSession(session);
        setQuestionContent(questionContent);
        setDollarValue(dollarValue);
        setDailyDouble(false);
    }

    //business methods
    public void displayQuestion(){
        String questionDisplay = "$" + getDollarValue();
        if(isSelected()){
            questionDisplay = "For $" + getDollarValue() +": " + questionContent;
        }
        if(isDailyDouble() && isSelected()){
            questionDisplay =("\u001B[44m \n \u001B[41m \u001B[30m" +
                    "*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*  D-A-I-L-Y   D-O-U-B-L-E  *$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*" +
                    "\n\u001B[44m" + "\n\u001B[0m" +
                    "For $" + getDollarValue()*2 + ": " + questionContent);
        }
        System.out.println(questionDisplay);
    }

    //Accessor methods
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isDailyDouble() {
        return isDailyDouble;
    }

    public void setDailyDouble(boolean dailyDouble) {
        if(Math.random() < 0.11){
            isDailyDouble = true;
        }
        if(dailyDouble){
            isDailyDouble = true;
        }
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public int getDollarValue() {
        return dollarValue;
    }

    public void setDollarValue(int dollarValue) {
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

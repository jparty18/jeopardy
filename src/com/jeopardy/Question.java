package com.jeopardy;

import java.util.Objects;

public class Question {

    private boolean isDailyDouble = false;
    private int session;
    private String questionContent;
    private int dollarValue;

    //ctor
    public Question(int session, String questionContent, int dollarValue) {
        setSession(session);
        setQuestionContent(questionContent);
        setDollarValue(dollarValue);
        setDailyDouble();
    }

    //Accessor methods
    public boolean isDailyDouble() {
        return isDailyDouble;
    }

    public void setDailyDouble() {
        if(Math.random() < 0.11){
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

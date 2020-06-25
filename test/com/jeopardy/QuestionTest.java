package com.jeopardy;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTest {
    Question trueFalseQuestion;
    Question multipleChoiceQuestion;
    List<String> answers = new ArrayList<>();

    @Before
    public void setup(){
        multipleChoiceQuestion = new MCQuestion(1, "An _______ is an actual object of some type", 200, "instance");
        trueFalseQuestion = new TFQuestion(1, "Every object instance has a type", 200, true);
    }

    @Test
    public void checkAnswer_Boolean_ShouldReturnTrueTest(){
        answers.add("False");
        trueFalseQuestion.showAnswerChoices(answers);
        assertTrue(trueFalseQuestion.checkAnswer(trueFalseQuestion.currentAnswerIndex));
    }

    @Test
    public void checkAnswer_Boolean_ShouldReturnFalseTest(){
        answers.add("False");
        trueFalseQuestion.showAnswerChoices(answers);
        assertFalse(trueFalseQuestion.checkAnswer(3));
    }

    @Test
    public void CheckAnswer_MultipleChoice_ShouldReturnTrueTest() {
        answers.add("Wrong answer 1");
        answers.add("Wrong answer 2");
        multipleChoiceQuestion.showAnswerChoices(answers);
        assertTrue(multipleChoiceQuestion.checkAnswer(multipleChoiceQuestion.currentAnswerIndex));
    }

    @Test
    public void checkAnswer_MultipleChoice_ShouldReturnFalseTest(){
        answers.add("Wrong answer 1");
        answers.add("Wrong answer 2");
        multipleChoiceQuestion.showAnswerChoices(answers);
        assertFalse(multipleChoiceQuestion.checkAnswer(4));
    }
}
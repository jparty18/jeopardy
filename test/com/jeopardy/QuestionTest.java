package com.jeopardy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuestionTest {
    Question trueFalseQuestion;
    Question multipleChoiceQuestion;

    @Before
    public void setup(){
        trueFalseQuestion = new MCQuestion(1, "An _______ is an actual object of some type", 200, "instance");
        multipleChoiceQuestion = new TFQuestion(1, "Every object instance has a type", 200, true);
    }

    @Test
    public void testSession(){
        assertEquals(1, multipleChoiceQuestion.getSession());
    }

    @Test
    public void testQuestionDisplayQuestion(){
        multipleChoiceQuestion.displayQuestion();
    }

}
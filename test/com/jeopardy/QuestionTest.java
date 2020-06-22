package com.jeopardy;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
    private Question q1;
    @Before
    public void setUp() {
        q1 = new Question(1, "What is a constructor?", 100);
    }

    @Test
    public void TestSession() {
        assertEquals(1, q1.getSession());
    }

    @Test
    public void getQuestionContent() {
        assertEquals("What is a constructor?", q1.getQuestionContent());
    }

    @Test
    public void getDollarValue() {
        assertEquals(100, q1.getDollarValue());
    }

}

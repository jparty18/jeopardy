package com.jeopardy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board easyBoard;
    Board hardBoard;
    Question mcQuestion;

    @Before
    public void setUp(){
        easyBoard = new Board(1, 10, 1);
        hardBoard = new Board(1, 5, 2);
        mcQuestion = new MCQuestion(1, "Java source files are compiled into ______ with _________",
                400, "bytecode & javac");
    }

    @Test
    public void getQuestions_shouldReturnMForTFQuestions_basedOnCorrectAnswerArgument() {
        easyBoard.getQuestions().forEach(question -> {
            if("True".equals(question.getAnswer()) || "False".equals(question.getAnswer())) {
                assertTrue(question instanceof TFQuestion);
            } else {
                assertTrue(question instanceof MCQuestion);
            }
        });
    }

    @Test
    public void getBoard_ShouldReturnBoardTest(){
        assertEquals(easyBoard, BoardFactory.createBoard(1, 10, 1));
        assertTrue(hardBoard.equals(BoardFactory.createBoard(1,5,2)));
    }

    @Test
    public void getContestants_shouldReturnRookieOrAdvancedPlayers_basedOnDifficulty(){
        easyBoard.getContestants().forEach(player -> {
            assertTrue(player instanceof RookiePlayer);
        });
    }

    @Test
    public void setMode_shouldReturnEnumMode() {
        assertEquals(Mode.EASY, easyBoard.getMode());
        easyBoard.setMode(2);
        assertEquals(Mode.HARD, easyBoard.getMode());
    }
}

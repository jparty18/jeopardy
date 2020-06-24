package com.jeopardy;

import org.junit.Before;
import org.junit.Test;
import com.jeopardy.Board;
import static org.junit.Assert.*;

public class BoardTest {
    Board gameBoard;
    Question mcQuestion;

    @Before
    public void setUp(){
        gameBoard = new Board(1, 18, 1);
        mcQuestion = new MCQuestion(1, "Java source files are compiled into ______ with _________",
                400, "bytecode & javac");
    }

        @Test
    public void getQuestions_shouldReturnQuestionsTest() {
            System.out.println(gameBoard.getQuestions());
            System.out.println(gameBoard.getContestants());
    }

    @Test
    public void getBoard_ShouldReturnBoardTest(){
        assertEquals(gameBoard, BoardFactory.createBoard(1, 3, 1));

    }

    @Test
    public void getAllContestants(){
        
    }

    public void testGetContestants() {
    }

    public void testGetQuestions() {
    }

    public void testGetAnswers() {
    }

    public void testStart() {
    }

}

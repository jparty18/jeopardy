package com.jeopardy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdvancedPlayerTest {

  Question mcQuestion;
  Question tfQuestion;
  Player player1 = new AdvancedPlayer("Expert1");
  Player player2 = new AdvancedPlayer("Expert2");

  @Before
  public void setUp() {
    mcQuestion = new MCQuestion(1, "Java source files are compiled into ______ with _________",
            400, "bytecode & javac");
    tfQuestion = new TFQuestion(1,"The JVM provides an independent operating environment for Java programs",
            200, true);
  }

  @Test
  public void testDeductScore_shouldDeductCurrentQuestionValueIfIncorrect() {
    //player1 got mcQuestion incorrect, score deducted
    assertEquals(-400, player1.deductScore(400));
    //player got tfQuestion incorrect, score deducted
    assertEquals(-200, player2.deductScore(200));
  }

  @Test
  public void testAskForHelp_noScoreChangeWhenAdvancedPlayerCallsForHelp() {
    //player1 asks for help for mcQuestion
    assertEquals(0, player1.askForHelp(400));
    //player2 asks for help for tqQuestion
    assertEquals(0, player2.askForHelp(200));
  }
}

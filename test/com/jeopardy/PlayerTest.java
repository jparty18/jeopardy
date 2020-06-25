package com.jeopardy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

  Question mcQuestion;
  Question tfQuestion;
  Player player1 = new RookiePlayer("Rookie");
  Player player2 = new AdvancedPlayer("Expert");

  @Before
  public void setUp(){
    mcQuestion = new MCQuestion(1, "Java source files are compiled into ______ with _________",
            400, "bytecode & javac");
    tfQuestion = new TFQuestion(1,"The JVM provides an independent operating environment for Java programs",
            200, true);
  }

  @Test
  public void testAddScore_shouldAddCurrentQuestionValueIfCorrect() {
    //player1 got mcQuestion correct, score added
    assertEquals(400, player1.addScore(400));
    //player2 got tqQuestion correct, score added
    assertEquals(200, player2.addScore(200));
  }
}

package com.jeopardy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RookiePlayerTest {

  Question mcQuestion;
  Question tfQuestion;
  Player player1 = new RookiePlayer("Rookie1");
  Player player2 = new RookiePlayer("Rookie2");

  @Before
  public void setUp() {
    mcQuestion = new MCQuestion(1, "Java source files are compiled into ______ with _________",
            400, "bytecode & javac");
    tfQuestion = new TFQuestion(1,"The JVM provides an independent operating environment for Java programs",
            200, true);
  }

  @Test
  public void testAskForHelp_shouldAddCurrentQuestionValueWhenCalledForHelp() {
    //player1 asks for help for mcQuestion, score added
    assertEquals(400, player1.askForHelp(400));
    //player2 asks for help for tfQuestion, score added
    assertEquals(200, player2.askForHelp(200));
  }
}

package com.jeopardy.sample;

import com.jeopardy.AdvancedPlayer;
import com.jeopardy.Expertise;
import com.jeopardy.Player;

import java.util.*;

public class Contestants {
  private static final List<Player> contestants = new ArrayList<>();

  public static List<Player> getContestants(int numberOfPlayers) {
    List<Player> results = new ArrayList();

    while(results.size() < numberOfPlayers) {
      int index = new Random().nextInt(contestants.size());
      //Done: Check if there is any duplicates
      results.add(contestants.get(index));
      contestants.remove(index);
    }

    return results;
  }

  static {
    contestants.add(new AdvancedPlayer("Chase", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Chris", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Danny", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("David", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Hunter", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Jane", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Jeffrey", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Joe", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Justin", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Levi", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Luke", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Malik", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Michael", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Ousmane", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Ricky", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Vincent", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Wei", Expertise.ADVANCED));
    contestants.add(new AdvancedPlayer("Wonil", Expertise.ADVANCED));
  }
}

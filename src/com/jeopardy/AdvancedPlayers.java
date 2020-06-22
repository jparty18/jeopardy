package Players;

public class AdvancedPlayers extends Players {

  public AdvancedPlayers () {

  }

  public AdvancedPlayers (String name, Expertise level) {
    super(name, level);
  }

  @Override//implement superClass abstract method
  public int deductScore(int currentQuestionValue) {
    int score = getScore();
    score -= currentQuestionValue;
    return score;
  }

  /*TODO:
    implement interface askForHelp()
 */
  @Override//implement interface abstract method
  public int askForHelp(int currentQuestionValue) {
    int result = 0;
    System.out.println("Skip this question and no value added or subtracted!");
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }


}


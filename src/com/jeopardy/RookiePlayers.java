package Players;

public class RookiePlayers extends Players {

  public RookiePlayers () {

  }

  public RookiePlayers (String name, Expertise level) {
    super(name, level);
  }

  @Override//implement superClass abstract method
  public int deductScore(int currentQuestionValue) {
    int score = getScore();
    return score;
  }

  /*TODO:
     implement interface askForHelp()
   */
  @Override//implement interface abstract method
  public int askForHelp(int currentQuestionValue) {
    int result = 0;
    System.out.println("Skip this question and value of this question automatically added!");
    result = result + currentQuestionValue;
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}

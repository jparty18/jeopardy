package Players;

  public abstract class Players implements Assistance{
    //instance variables
    private String name;
    private Expertise level;

    private int score = 0;
    private boolean isCorrect = true;


    //ctors
    public Players (){

    }

    public Players (String name, Expertise level) {
      setName(name);
      setLevel(level);
    }

    //business methods
    public int addScore(int currentQuestionValue) {
      int score = getScore();
      score += currentQuestionValue;
      return score;
    }

    public abstract int deductScore(int currentQuestionValue);

    //accessor methods
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }

    public Expertise getLevel() {
      return level;
    }
    public void setLevel(Expertise level) {
      this.level = level;
    }

    public int getScore() {
      return score;
    }
    public void setScore(int score) {
      this.score = score;
    }

    public boolean isCorrect() {
      return isCorrect;
    }
    public void setCorrect(boolean correct) {
      isCorrect = correct;
    }

   //toString()
    public String toString() {
      return getClass().getSimpleName() + ": name=" + getName() + " level=" + getLevel();
    }

}

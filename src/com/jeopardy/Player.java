package com.jeopardy;

  public abstract class Player implements Assistance{
    //instance variables
    private String name;
    private int score = 0;
    private Expertise level;

    public Player(String name) {
      setName(name);
    }

    //business methods
    public int addScore(int currentQuestionValue) {
      int score = getScore();
      score += currentQuestionValue;
      this.setScore(score);
      return score;
    }

    public int deductScore(int currentQuestionValue) {
      // do nothing
      return getScore();
    };

    //accessor methods
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public Expertise getLevel() {
      return level;
    }
    public void setLevel(Expertise level) {
      this.level = level;
    }

    //toString()
    public String toString() {
      return getClass().getSimpleName() + ": name=" + getName() + " level=" + getLevel();
    }

}

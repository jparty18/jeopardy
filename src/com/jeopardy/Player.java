package com.jeopardy;

  public abstract class Player implements Assistance{
    //instance variables
    private String name;
    private int score = 0;
    private boolean needHelp = false;

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
    private void setName(String name) {
      this.name = name;
    }

    public int getScore() { return score; }
    void setScore(int score) { this.score = score; }

    public boolean isNeedHelp() {
      return needHelp;
    }
    public void setNeedHelp() {
      this.needHelp = !isNeedHelp();
    }

    //toString()
    public String toString() {
      return getClass().getSimpleName() + ": name=" + getName();
    }

}

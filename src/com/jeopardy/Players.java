package com.jeopardy;

  public abstract class Players implements Assistance{
    //instance variables
    private String name;
    private Expertise level;

    //ctors
    public Players (){

    }

    public Players (String name, Expertise level) {
      setName(name);
      setLevel(level);
    }

    //business methods
    public abstract int calculateFinalScore();

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

    //toString()
    public String toString() {
      return getClass().getSimpleName() + ": name=" + getName() + " level=" + getLevel();
    }

}

package com.jeopardy.sample;

import com.jeopardy.Question;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Questions {
  private static final List<Question> questions = new ArrayList<>();

  public static List<Question> getQuestions(int session) {
    List<Question> results = questions.stream()
            .filter(question -> question.getSession() == session)
            .sorted(Comparator.comparing(question -> question.getDollarValue()))
            .collect(Collectors.toList());

    return results;
  }

  static {
    questions.add(new Question(1, "Java source files have a _________ extension", 200, ".java"));
    questions.add(new Question(1, "The JVM provides an independent operating environment for " +
            "Java programs", 200, true));
    questions.add(new Question(1, "The _________ method is the entry point for all Java programs", 200, "main()"));
    questions.add(new Question(1, "Java source files are compiled into ______ with _________", 400, "bytecode, javac"));
    questions.add(new Question(1, "Java programs can not run unchanged in different environments " +
            "and operating systems." , 200, false));
  }
}

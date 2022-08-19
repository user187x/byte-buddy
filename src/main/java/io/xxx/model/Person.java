package io.xxx.model;

public class Person implements Worker {

  @Override
  public void work(String action) {
    System.out.println("I am a Person asinged to action [" + action + "]");
  }
}

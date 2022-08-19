package io.xxx.model;

public class Robot implements Worker {

  @Override
  public void work(String action) {
    System.out.println("I am a Robot asinged to action [" + action + "]");
  }
}

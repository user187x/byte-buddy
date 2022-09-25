package io.xxx.model;

public class Human implements Animal {
  
  private String name = "John";
  private String task = "Working";
  
  public Human() {}
  
  public Human(String task) {
    this.task = task;
  }
  
  @Override
  public String invoke(String task) {
    
    this.task = task;
    String message = "I'm the Human " + name + " assinged to " + task;
    
    System.out.println(message);
    
    return message;
  }

  @Override
  public String getName() {
    return name;
  }
  
  public String getTask() {
    return task;
  }
}
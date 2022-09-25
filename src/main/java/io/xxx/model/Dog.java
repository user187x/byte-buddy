package io.xxx.model;

public class Dog implements Animal {
  
  private String name = "Spike";
  private String task = "Sleeping";
  
  public Dog() {}
  
  public Dog(String task) {
    this.task = task;
  }
  
  @Override
  public String invoke(String task) {
    
    this.task = task;
    String message = "I'm the Dog " + name + " assinged to " + task;
    
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

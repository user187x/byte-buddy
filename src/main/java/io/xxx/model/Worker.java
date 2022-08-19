package io.xxx.model;

public interface Worker {

  public void work(String action);
  
  public default void before() {
    System.out.println("Before");
  }
  
  public default void after() {
    System.out.println("After");
  }
}

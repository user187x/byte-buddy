package io.xxx;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import io.xxx.model.Person;
import io.xxx.model.Robot;

public class App {
  
  public static void main(String[] args) throws Exception {
    
    Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(() -> {
      
      new Person().work("dig");
      new Robot().work("compute");
      
    }, 0, 1, TimeUnit.SECONDS);
  }
}

package io.xxx;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
  
  public static void main(String[] args) throws Exception {
    
//    App app = new ByteBuddy()
//    .subclass(App.class)
//    .method(ElementMatchers.named("trigger"))
//    .intercept(MethodCall.invoke(App.class.getMethod("before"))
//    .andThen(SuperMethodCall.INSTANCE)
//    .andThen(MethodCall.invoke(App.class.getMethod("after"))))
//    .make()
//    .load(App.class.getClassLoader())
//    .getLoaded()
//    .getDeclaredConstructor()
//    .newInstance();
//    
//    app.trigger();
    
    
    App app = new App();
    
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleWithFixedDelay(() -> app.trigger("test", 230L), 0, 1, TimeUnit.SECONDS);
    
    //This method should NOT be intercepted
    app.before();
    
    //This should be intercepted
    app.after();
  }
  
  /**
   * This method is the target of interception
   * 
   * @param value
   * @param size
   */
  public void trigger(String value, long size) {
    
    System.out.println("trigger method invoked " + new Date().toString());
    //Pretend busy work happening here
  }
  
  public void before() {
    System.out.println("Before");
  }
  
  public void after() {
    System.out.println("After");
  }
}

package io.xxx;

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
    app.format("test", 230L);
    app.trigger();
  }
  
  public void format(String value, long size) {
    
    //Doing busy work
  }
  
  public void trigger() {
    
    //Should not be intercepted
    System.out.println("trigger");
  }
  
  public void before() {
    System.out.println("Before");
  }
  
  public void after() {
    System.out.println("After");
  }
}

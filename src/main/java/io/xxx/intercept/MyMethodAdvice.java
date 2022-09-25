package io.xxx.intercept;

import io.xxx.model.Dog;
import net.bytebuddy.asm.Advice;

public class MyMethodAdvice {

  @Advice.OnMethodEnter(suppress = Throwable.class)
  public static void onEnter(
      @Advice.This Object self,
      @Advice.Origin String origin,
      @Advice.AllArguments Object[] args,
      @Advice.Local("myLocalVar") String myLocalVar) {
    
    myLocalVar = "Passing message to the onExit method";
    
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        System.out.println("Argument: [" + i + "] = " + args[i]);
      }
    }
    
    System.out.println("Origin :" + origin);
    
    if(self instanceof Dog)
      System.out.println("Giving advice to Dog class");
  }
  
  @Advice.OnMethodExit(suppress = Throwable.class, onThrowable = Throwable.class)
  public static void onExit(
      @Advice.Local("myLocalVar") String myLocalVar) {
    
    System.out.println("Inside exit method : Got message from onEnter method --> " + myLocalVar);
  }
}

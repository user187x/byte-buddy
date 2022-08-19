package io.xxx.util;

import io.xxx.model.Person;
import io.xxx.model.Worker;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class Hijacker {

  public static void doWork() throws Exception {
    
    new ByteBuddy()
    .subclass(Person.class)
    .method(ElementMatchers.named("work"))
    .intercept(MethodCall.invoke(Worker.class.getMethod("before"))
      .andThen(SuperMethodCall.INSTANCE)
      .andThen(MethodCall.invoke(Worker.class.getMethod("after"))))
    .make()
    .load(Worker.class.getClassLoader())
    .getLoaded()
    .getDeclaredConstructor()
    .newInstance()
    .work("clean");
  }
}

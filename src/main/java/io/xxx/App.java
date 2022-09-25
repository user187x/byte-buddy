package io.xxx;

import io.xxx.intercept.MyMethodAdvice;
import io.xxx.intercept.MyMethodDelegate;
import io.xxx.model.Animal;
import io.xxx.model.Human;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class App {
  
  public static void main(String[] args) throws Exception {

    doMethodDelagation(new Human());
    //doMethodAdvice(new Dog());
  }
  
  public static void doMethodDelagation(Animal animal) throws Exception {
    
    new ByteBuddy()
    .subclass(animal.getClass())
    .method(ElementMatchers.any())
    .intercept(MethodDelegation.to(MyMethodDelegate.class))
    .make()
    .load(animal.getClass().getClassLoader())
    .getLoaded()
    .getDeclaredConstructor()
    .newInstance()
    .invoke("Petting");
  }
  
  public static void doMethodAdvice(Animal animal) throws Exception {
    
    new ByteBuddy()
    .subclass(animal.getClass())
    .method(ElementMatchers.any())
    .intercept(Advice.to(MyMethodAdvice.class))
    .make()
    .load(animal.getClass().getClassLoader())
    .getLoaded()
    .getDeclaredConstructor()
    .newInstance()
    .invoke("Barking");
  }
}

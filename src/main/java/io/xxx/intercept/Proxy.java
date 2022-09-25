package io.xxx.intercept;

import java.lang.reflect.Method;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Empty;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperMethod;
import net.bytebuddy.implementation.bind.annotation.This;

public class Proxy {
  
  @RuntimeType
  public static Object intercept(
      @This Object self, 
      @Origin Method method,
      @AllArguments Object[] args,
      @SuperMethod(nullIfImpossible = true) Method superMethod,
      @Empty Object defaultValue) throws Throwable {

    if(superMethod == null)
      return defaultValue;
    
    System.out.println("I'm the proxy --> " + method.getName());

    Object object = superMethod.invoke(self, args);

    return object;
  }
}
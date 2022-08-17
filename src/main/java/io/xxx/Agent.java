package io.xxx;

import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.Advice.Argument;
import net.bytebuddy.asm.Advice.Enter;
import net.bytebuddy.asm.Advice.OnMethodEnter;
import net.bytebuddy.asm.Advice.OnMethodExit;
import net.bytebuddy.asm.Advice.Origin;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {

  public static void premain(String args, Instrumentation instrumentation) {

    new AgentBuilder.Default().type(ElementMatchers.any())
    .transform((builder, typeDescription, classLoader, module, protectionDomain) -> builder
    .method(ElementMatchers.named("trigger"))
    .intercept(Advice.to(TriggerInterceptor.class))
    .method(ElementMatchers.named("after"))
    .intercept(Advice.to(AfterInterceptor.class)))
    .installOn(instrumentation);
  }

  public static class TriggerInterceptor {

    @OnMethodEnter
    public static long enter(@Origin String method, @Argument(value = 0) String value, @Argument(value = 1) long size) throws Exception {

      long startEpoch = System.currentTimeMillis();
      System.out.println("value passed in : " + value + " size : " + size) ;

      return startEpoch;
    }

    @OnMethodExit
    public static void exit(@Origin String method, @Enter long startEpoch) throws Exception {

      long endEpoch = System.currentTimeMillis();
      long duration = endEpoch - startEpoch;

      System.out.println(method + " elapsed " + duration + " ms");
    }
  }
  
  public static class AfterInterceptor {

    @OnMethodEnter
    public static void enter() throws Exception {

      System.out.println("Intercpeted after method") ;
    }
  }
}

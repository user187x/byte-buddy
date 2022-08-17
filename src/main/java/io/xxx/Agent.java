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
    .method(ElementMatchers.named("format"))
    .intercept(Advice.to(ValueInterceptor.class)))
    .installOn(instrumentation);
  }

  public static class ValueInterceptor {

    @OnMethodEnter
    static long enter(@Origin String method, @Argument(value = 0) String value, @Argument(value = 1) long size) throws Exception {

      long startEpoch = System.currentTimeMillis();

      System.out.println("value passed in : " + value + " size : " + size) ;

      return startEpoch;
    }

    @OnMethodExit
    static void exit(@Origin String method, @Enter long startEpoch) throws Exception {

      long endEpoch = System.currentTimeMillis();
      long duration = endEpoch - startEpoch;

      System.out.println(method + " elapsed " + duration + " ms");
    }
  }
}

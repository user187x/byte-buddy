package io.xxx;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import io.xxx.model.Worker;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.Advice.Argument;
import net.bytebuddy.asm.Advice.OnMethodEnter;
import net.bytebuddy.asm.Advice.OnMethodExit;
import net.bytebuddy.asm.Advice.Origin;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {

  public static void premain(String args, Instrumentation instrumentation) {

    new AgentBuilder.Default()
    .type(ElementMatchers.isSubTypeOf(Worker.class))
    .transform((builder, typeDescription, classLoader, module, protectionDomain) -> builder
    .method(ElementMatchers.named("work"))
    .intercept(Advice.to(TriggerInterceptor.class)))
    .installOn(instrumentation);
  }

  public static class TriggerInterceptor {

    @OnMethodEnter
    public static void enter(
        @Advice.Origin Class<?> clazz,
        @Advice.Local("epoch") long epoch,
        @Origin String method, 
        @Argument(value = 0) String value) throws Exception {

      epoch = System.currentTimeMillis();
      System.out.println("Value intercepted [" + value + "] form class [" + clazz.getSimpleName() + "]") ;
    }

    @OnMethodExit
    public static void exit(
        @Origin Method method,
        @Advice.Local("epoch") long epoch) throws Exception {

      long endEpoch = System.currentTimeMillis();
      long duration = endEpoch - epoch;

      System.out.println("Method [" + method.getName() + "] elapsed " + duration + " ms");
    }
  }
}

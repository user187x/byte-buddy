package io.xxx.agent;

import java.lang.instrument.Instrumentation;
import io.xxx.intercept.MyMethodDelegate;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {

  public static void premain(String args, Instrumentation instrumentation) {

    new AgentBuilder.Default()
    .type(ElementMatchers.any())
    .transform((builder, typeDescription, classLoader, module, protectionDomain) -> builder
    .method(ElementMatchers.named("getJob").and(ElementMatchers.returns(String.class)))
    .intercept(MethodDelegation.to(MyMethodDelegate.class).andThen(SuperMethodCall.INSTANCE)))
    .installOn(instrumentation);
  }
}

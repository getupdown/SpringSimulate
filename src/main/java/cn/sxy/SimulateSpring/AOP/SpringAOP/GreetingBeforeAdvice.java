package cn.sxy.SimulateSpring.AOP.SpringAOP;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

// SpringAOP的前置增强
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before");
    }
}

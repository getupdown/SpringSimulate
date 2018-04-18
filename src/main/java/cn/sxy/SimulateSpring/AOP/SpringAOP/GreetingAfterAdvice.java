package cn.sxy.SimulateSpring.AOP.SpringAOP;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

// SpringAOP 后置增强
public class GreetingAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After");
    }
}

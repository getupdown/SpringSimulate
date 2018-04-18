package cn.sxy.SimulateSpring.AOP.SpringAOP;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class GreetingThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("------------Throw Exception------------");
        System.out.println("Exception Message: " + e.getMessage());
        System.out.println("---------------------------------------");
    }
}

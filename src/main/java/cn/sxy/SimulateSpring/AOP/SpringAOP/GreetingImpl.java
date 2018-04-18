package cn.sxy.SimulateSpring.AOP.SpringAOP;

import org.springframework.stereotype.Component;

@Component("greetingImpl")
public class GreetingImpl implements Greeting {

    public void greeting() {
        System.out.println("Hello!");
    }

    @Override
    public void greetingWithException() {
        System.out.println("greetingWithException");

        throw new RuntimeException("Error");
    }
}

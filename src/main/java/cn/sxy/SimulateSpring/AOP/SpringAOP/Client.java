package cn.sxy.SimulateSpring.AOP.SpringAOP;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());
        proxyFactory.addAdvice(new GreetingThrowAdvice());

        GreetingImpl greeting = (GreetingImpl) proxyFactory.getProxy();

        greeting.greetingWithException();
//        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
//
//        Greeting greeting = (Greeting) context.getBean("greetingProxy");
//        greeting.greeting();
    }
}

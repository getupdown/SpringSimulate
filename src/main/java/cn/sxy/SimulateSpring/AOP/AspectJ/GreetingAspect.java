package cn.sxy.SimulateSpring.AOP.AspectJ;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {
    @DeclareParents(value = "cn.sxy.SimulateSpring.AOP.AspectJ.FuckingImpl", defaultImpl = WorkImpl.class)
    private Work work;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Fucking fucking = (Fucking) context.getBean("fuckingImpl");

        fucking.fuck();

        System.out.println(fucking.getClass().toString());

        Work work = (Work) fucking;

        work.work();

    }
}

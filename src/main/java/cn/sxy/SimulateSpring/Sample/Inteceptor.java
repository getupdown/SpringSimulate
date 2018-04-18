package cn.sxy.SimulateSpring.Sample;

import cn.sxy.SimulateSpring.AOP.Annotation.MyAfter;
import cn.sxy.SimulateSpring.AOP.Annotation.MyAspect;
import cn.sxy.SimulateSpring.AOP.Annotation.MyBefore;

@MyAspect
public class Inteceptor {

    public Inteceptor() {
    }

    @MyBefore
    public void before() {
        System.out.println("beforeasdf");
    }

    @MyAfter
    public void after() {
        System.out.println("after");
    }
}

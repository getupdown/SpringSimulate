package cn.sxy.SimulateSpring.AOP.DynamicProxy.ProxyPackage;

import cn.sxy.SimulateSpring.AOP.Annotation.MyAfter;
import cn.sxy.SimulateSpring.AOP.Annotation.MyBefore;

public class ProxyAux {

    @MyBefore
    public void mybefore() {
        System.out.println("mybefore");
    }

    @MyAfter
    public void myafter() {
        System.out.println("myafter");
    }
}

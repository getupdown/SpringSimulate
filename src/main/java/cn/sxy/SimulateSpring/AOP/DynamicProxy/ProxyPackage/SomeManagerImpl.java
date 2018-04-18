package cn.sxy.SimulateSpring.AOP.DynamicProxy.ProxyPackage;

import cn.sxy.SimulateSpring.AOP.Annotation.NeedAdvice;

public class SomeManagerImpl implements SomeManager {

    @Override
    @NeedAdvice
    public void treat() {
        System.out.println("manager treating");
    }

    @Override
    public void other() {
        System.out.println("asidfjiasdjf");
    }
}

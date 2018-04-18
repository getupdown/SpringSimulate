package cn.sxy.SimulateSpring.AOP.DynamicProxy.ProxyPackage;

import java.lang.reflect.Method;

import cn.sxy.SimulateSpring.AOP.Advicer.AOPCGLibProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {

    // 现在我这里没有很好的方法可以把各个方法分开来,
    // 所以我只能上判断了
    // 暂时无法解决多重代理(嵌套)
    // 于是这个method参数是一个重要玄机，因为通过这个我就可以获取注解了
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object res = methodProxy.invokeSuper(obj, args);
        after();
        return res;
    }

    public void before() {
        System.out.println("before!");
    }

    public void after() {
        System.out.println("after!");
    }

    public static void main(String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
//        SomeManagerImpl managerProxy = (SomeManagerImpl) Enhancer.create(SomeManagerImpl.class, cgLibProxy);
//        // 现在这个里面是对所有的方法都进行了加强
//        // 我觉得玄机就在create方法里的一些东西
//        System.out.println(managerProxy.getClass());
//        managerProxy.treat();
//        managerProxy.other();

        AOPCGLibProxy aopcgLibProxy = new AOPCGLibProxy(new ProxyAux());
        SomeManagerImpl manager = (SomeManagerImpl) Enhancer.create(SomeManagerImpl.class, aopcgLibProxy);
        manager.other();
        manager.treat();
    }
}

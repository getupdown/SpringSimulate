package cn.sxy.SimulateSpring.AOP.DynamicProxy.ProxyPackage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(this.object, args);
        after();
        return null;
    }

    private void before() {
        System.out.println("Method begin! ");
    }

    private void after() {
        System.out.println("Method end! ");
    }

    public static void main(String[] args) {

        SomeManager someManager = new SomeManagerImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(someManager);

        // 很细节的代码是看不到了，但是出来的类是com.sun.proxy.$Proxy0
        // 他会把那些interface带下去，所以出来的这个proxy一定也是实现了你的接口
        // 所以一定要用接口来转换
        SomeManager someManagerProxy = (SomeManager) Proxy.newProxyInstance(
                someManager.getClass().getClassLoader(),
                someManager.getClass().getInterfaces(),
                dynamicProxy
        );

        someManagerProxy.treat();
    }
}

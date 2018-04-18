package cn.sxy.SimulateSpring.AOP.Advicer;

import java.lang.reflect.Method;
import java.util.Arrays;

import cn.sxy.SimulateSpring.AOP.Annotation.MyAfter;
import cn.sxy.SimulateSpring.AOP.Annotation.MyBefore;
import cn.sxy.SimulateSpring.AOP.Annotation.NeedAdvice;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AOPCGLibProxy implements MethodInterceptor {
    // 现在我这里没有很好的方法可以把各个方法分开来,
    // 所以我只能上判断了
    // 暂时无法解决多重代理(嵌套)
    // 于是这个method参数是一个重要玄机，因为通过这个我就可以获取注解了
    // 接下来就是想办法把这些before和after方法放到外面去实现
    // 于是我需要借助一个东西
    // 到时候就用那个@MyAspect的类作为aux加入到这个地方来
    private Object aux;

    private Method beforeMethod;

    private Method afterMethod;

    public AOPCGLibProxy(Object aux) {
        this.aux = aux;

        Arrays.stream(aux.getClass().getDeclaredMethods()).forEach(method -> {
            if (withMyBefore(method)) {
                beforeMethod = method;
            } else if (withMyAfter(method)) {
                afterMethod = method;
            }
        });
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (needAdvice(method)) {
            if (beforeMethod != null) {
                beforeMethod.invoke(aux);
            }
            Object res = methodProxy.invokeSuper(obj, args);
            if (afterMethod != null) {
                afterMethod.invoke(aux);
            }
            return res;
        } else {
            return methodProxy.invokeSuper(obj, args);
        }
    }

    private boolean needAdvice(Method method) {
        return Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType() ==
                NeedAdvice.class);
    }

    private boolean withMyAfter(Method method) {
        return Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType() ==
                MyAfter.class);
    }

    private boolean withMyBefore(Method method) {
        return Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType() ==
                MyBefore.class);
    }
}

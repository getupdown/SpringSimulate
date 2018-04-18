package cn.sxy.SimulateSpring.AOP.Advicer;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import cn.sxy.SimulateSpring.AOP.Annotation.MyAspect;
import cn.sxy.SimulateSpring.AOP.Annotation.NeedAdvice;
import cn.sxy.SimulateSpring.IOC.BeanManager.ClassUtils;
import net.sf.cglib.proxy.Enhancer;

public class BeanAdvicer {

    // 这部要在注入前做
    public static Map<Class, Object> adviceBean(Set<Class> allClass, Map<Class, Object> classObjectMap) {

        Set<Class> classNeedAdviceSet = ClassUtils.getClassMethodsWithXAnnotation(allClass,
                NeedAdvice.class);

        Set<Class> classAux = ClassUtils.getClassWithXAnnotation(allClass, MyAspect.class);

        // 先规定只能有一个横切逻辑
        assert classAux.size() == 1;

        try {

            Object auxObject = classAux.toArray(new Class[0])[0].newInstance();

            // 用增强类替换里面的实体
            for (Class clazz : classNeedAdviceSet) {
                if (!classObjectMap.containsKey(clazz)) {
                    continue;
                }
                classObjectMap.put(clazz, Enhancer.create(clazz, new AOPCGLibProxy(auxObject)));
            }

            return classObjectMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean needAdvice(Class clazz) {
        return Arrays.stream(clazz.getDeclaredMethods()).anyMatch(method ->
                Arrays.stream(method.getDeclaredAnnotations())
                        .anyMatch(annotation -> annotation.annotationType() == NeedAdvice.class)
        );
    }
}

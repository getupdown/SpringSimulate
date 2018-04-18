package cn.sxy.SimulateSpring.IOC.BeanManager;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.sxy.SimulateSpring.AOP.Advicer.BeanAdvicer;

public class BeanManager {
    private static Map<Class, Object> beanMap = new ConcurrentHashMap<>();

    public static Object getBean(Class clazz) {
        return beanMap.get(clazz);
    }

    public static void constructBean(String rootPackageName) {

        Set<Class> allClass = ClassUtils.getClassesUnderPackage(rootPackageName);

        // 先把所有对象构造出来
        Map<Class, Object> tmpMap = BeanConstructor.constructBeanUnderPackage(allClass);

        // 然后用增强类去替换
        tmpMap = BeanAdvicer.adviceBean(allClass, tmpMap);

        // 然后注入
        BeanInjector.injectBean(tmpMap);

        beanMap = tmpMap;
    }
}

package cn.sxy.SimulateSpring.IOC.BeanManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.sxy.SimulateSpring.IOC.Injector.AbstractInjector;
import cn.sxy.SimulateSpring.IOC.Injector.AutowiredInjector;
import cn.sxy.SimulateSpring.IOC.Injector.StringValueInjector;

public class BeanInjector {

    // todo 这里可以写的骚一点,比如说用之前那个扫描器
    private static List<AbstractInjector> injectorList =
            Stream.of(new AutowiredInjector(),
                    new StringValueInjector()).
                    collect(Collectors.toList());

    public static void injectBean(Map<Class, Object> beanMap) {
        beanMap.forEach((clazz, object) ->
                injectSingleBean(clazz, object, beanMap)
        );
    }

    private static void injectSingleBean(Class clazz, Object object, Map<Class, Object> beanMap) {
        injectorList.forEach(injector -> injector.inject(clazz, object, beanMap));
    }

}

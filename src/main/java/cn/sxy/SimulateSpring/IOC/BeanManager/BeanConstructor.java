package cn.sxy.SimulateSpring.IOC.BeanManager;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.sxy.SimulateSpring.IOC.Annotation.Service;

public class BeanConstructor {

    // todo 这里可以用FileUtils代替
    private static Set<Class> beanClassAnnotations = Stream.of(Service.class)
            .collect(Collectors.toSet());

    public static ConcurrentHashMap<Class, Object> constructBeanUnderPackage(Set<Class> classSet) {

        ConcurrentHashMap<Class, Object> hashMap = new ConcurrentHashMap();
        for (Class clazz : classSet) {
            if (!needToConstruct(clazz)) {
                continue;
            }
            // 这里实例化对象, 放入map里, 一定要有默认的构造函数
            try {
                Object clazzObject = clazz.newInstance();
                hashMap.put(clazz, clazzObject);
                //todo 判断这里是否是接口，暂时只支持接口，class和基本类型， 集合类型不支持
            } catch (InstantiationException | IllegalAccessException e) {
                System.out.println("Instantiation Failed! 一定要有无参构造函数");
            }
        }
        return hashMap;
    }

    // 需要预先实例化的类, 保存在beanClassAnnotations的集合里
    private static boolean needToConstruct(Class beanClass) {
        return Arrays.stream(beanClass.getAnnotations()).anyMatch(annotation -> beanClassAnnotations
                .contains(annotation.annotationType()));
        //todo annotationType
    }

    public static void main(String args[]) {

    }

}

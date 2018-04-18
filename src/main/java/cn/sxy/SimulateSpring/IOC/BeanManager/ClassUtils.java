package cn.sxy.SimulateSpring.IOC.BeanManager;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {

    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public static Set<Class> getClassesUnderPackage(String packageName) {
        Set<Class> set = new HashSet<>();
        recursiveAddClass(packageName, set);
        return set;
    }

    private static void recursiveAddClass(String rootPackageName, Set<Class> classSet) {
        URL url = classLoader.getResource(rootPackageName);
        File rootFolder = new File(url.getFile());

        if (rootFolder.listFiles() != null) {
            for (File file : rootFolder.listFiles()) {
                if (file.isDirectory()) {
                    recursiveAddClass(rootPackageName + "/" + file.getName(), classSet);

                } else if (file.getName().endsWith(".class")) {
                    try {
                        Class clazz = classLoader.loadClass(rootPackageName.replace("/", ".")
                                + "." + file.getName().substring(0, file.getName().indexOf(".class")));
                        classSet.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 判断类上面的注解
    public static boolean isClassWithXAnnotation(Class clazz, Class anno) {
        return Arrays.stream(clazz.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType() ==
                anno);
    }

    // 判断类里面的方法有没有某个注解
    public static boolean isClassMethodsWithXAnnotation(Class clazz, Class anno) {
        return Arrays.stream(clazz.getDeclaredMethods()).anyMatch(method ->
                Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation.annotationType() ==
                        anno)
        );
    }

    public static Set<Class> getClassWithXAnnotation(Set<Class> classSet, Class anno) {
        Set<Class> set = new HashSet<>();
        for (Class aClass : classSet) {
            if (isClassWithXAnnotation(aClass, anno)) {
                set.add(aClass);
            }
        }
        return set;
    }

    public static Set<Class> getClassMethodsWithXAnnotation(Set<Class> classSet, Class anno) {
        Set<Class> set = new HashSet<>();
        for (Class aClass : classSet) {
            if (isClassMethodsWithXAnnotation(aClass, anno)) {
                set.add(aClass);
            }
        }
        return set;
    }
}
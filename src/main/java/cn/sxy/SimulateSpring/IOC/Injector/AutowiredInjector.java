package cn.sxy.SimulateSpring.IOC.Injector;

import java.lang.reflect.Field;
import java.util.Arrays;

import cn.sxy.SimulateSpring.IOC.Annotation.Autowired;

public class AutowiredInjector extends AbstractInjector {

    @Override
    protected boolean needToInject(Field field) {
        return Arrays.stream(field.getAnnotations()).anyMatch(annotation ->
           annotation.annotationType() == Autowired.class
        );
    }
}

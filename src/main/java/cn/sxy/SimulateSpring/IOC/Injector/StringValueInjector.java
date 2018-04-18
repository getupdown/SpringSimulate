package cn.sxy.SimulateSpring.IOC.Injector;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import cn.sxy.SimulateSpring.IOC.Annotation.StringValue;

public class StringValueInjector extends AbstractInjector {
    @Override
    protected boolean needToInject(Field field) {
        return Arrays.stream(field.getAnnotations()).anyMatch(annotation ->
                annotation.annotationType() == StringValue.class
        );
    }

    @Override
    protected void setField(Field field, Object object, Map<Class, Object> beanMap) throws Exception {
        field.setAccessible(true);
        String value = field.getAnnotation(StringValue.class).value();
        field.set(object, value);
    }
}

package cn.sxy.SimulateSpring.IOC.Injector;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public abstract class AbstractInjector {

    public void inject(Class clazz, Object object, Map<Class, Object> beanMap) {
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            if (needToInject(field)) {
                try {
                    setField(field, object, beanMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected abstract boolean needToInject(Field field);

    protected void setField(Field field, Object object, Map<Class, Object> beanMap) throws Exception {
        field.setAccessible(true);
        field.set(object, beanMap.get(field.getType()));
    }
}

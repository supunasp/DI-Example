package it.supunasp.di.injector;

import it.supunasp.di.annotate.AutoWired;
import it.supunasp.di.annotate.ScopeType;
import it.supunasp.di.annotate.ServiceScope;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AutoWiredInjector {

    private Map<String, Object> instanceMap = new HashMap<>();

    public void injectBeans(Object object) throws Exception {
        if (Objects.isNull(object)) {
            throw new Exception("Object is null ");
        }

        Class<?> objectClass = object.getClass();

        initializeFields(object, objectClass);
    }

    private void initializeFields(Object object, Class<?> objectClass) throws IllegalAccessException, InstantiationException {

        for (Field declaredField : objectClass.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(AutoWired.class)) {

                boolean accessible = declaredField.isAccessible();

                declaredField.setAccessible(true);

                Class<?> fieldClass = declaredField.getType();

                if (fieldClass.isAnnotationPresent(ServiceScope.class)) {
                    if (!fieldClass.isInterface()) {
                        ScopeType scopeType = fieldClass.getAnnotation(ServiceScope.class).value();
                        Object fieldValue = getServiceInstance(fieldClass, scopeType);
                        if (fieldValue != null) {
                            declaredField.set(object, fieldValue);
                        } else {
                            throw new InstantiationException("Error : cannot initialize " + fieldClass.getCanonicalName());
                        }
                    } else {
                        throw new InstantiationException("Error : cannot initialize an Interface " + fieldClass.getCanonicalName());
                    }
                }
                declaredField.setAccessible(accessible);
            }
        }
        if (objectClass.getSuperclass() != null) {
            initializeFields(object, objectClass.getSuperclass());
        }
    }

    private Object getServiceInstance(Class<?> fieldClass, ScopeType scopeType) {

        Object instance = null;

        try {
            switch (scopeType) {
                case PROTOTYPE:

                    instance = getInstanceOfClass(fieldClass);
                    injectBeans(instance);

                    break;
                case SINGLETON:
                    if (this.instanceMap.containsKey(fieldClass.getCanonicalName())) {
                        instance = instanceMap.get(fieldClass.getCanonicalName());
                    } else {
                        instance = getInstanceOfClass(fieldClass);
                        injectBeans(instance);
                        instanceMap.put(fieldClass.getCanonicalName(), instance);
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("Not implemented, yet");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    public Object getInstanceOfClass(Class<?> fieldClass) throws IllegalAccessException, InstantiationException {
        return fieldClass.newInstance();
    }

}

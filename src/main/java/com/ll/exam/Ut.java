package com.ll.exam;

import java.lang.reflect.InvocationTargetException;

public class Ut {
    public static class cls {
        // 해당 클래스 타입의 객체 생성(Object 버전)
        public static Object newObj(Class cls, Object defaultValue) {
            try {
                return cls.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                return defaultValue;
            } catch (IllegalAccessException e) {
                return defaultValue;
            } catch (InvocationTargetException e) {
                return defaultValue;
            } catch (NoSuchMethodException e) {
                return defaultValue;
            }
        }
    }
}

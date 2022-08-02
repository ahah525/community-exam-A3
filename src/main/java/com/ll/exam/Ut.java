package com.ll.exam;

import java.lang.reflect.InvocationTargetException;

public class Ut {
    public static class cls {
        // 해당 클래스 타입의 객체 생성(Generic 버전)
        public static <T> T newObj(Class<T> cls, T defaultValue) {
            try {
                // 객체 생성
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

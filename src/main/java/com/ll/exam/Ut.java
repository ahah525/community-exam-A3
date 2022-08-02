package com.ll.exam;

import java.lang.reflect.Field;
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

    public static class str {
        public static String decapitalize(String string) {
            if (string == null || string.length() == 0) {
                return string;
            }
            char c[] = string.toCharArray();
            c[0] = Character.toLowerCase(c[0]);
            return new String(c);
        }
    }

    public static class reflection {
        // 필드명으로 객체의 필드 값 반환하는 메서드
        public static <T> T getFieldValue(Object o, String fieldName, T defaultValue) {
            Field field = null;

            try {
                field = o.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return defaultValue;
            }

            field.setAccessible(true);

            try {
                return (T)field.get(o);
            } catch (IllegalAccessException e) {
                return defaultValue;
            }
        }
    }
}

package com.ll.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메서드에 붙는 어노테이션임
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMapping {
    String value(); // @GetMapping(value=String)형태로 입력O
}
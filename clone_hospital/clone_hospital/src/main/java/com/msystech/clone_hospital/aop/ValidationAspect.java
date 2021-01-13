package com.msystech.clone_hospital.aop;


import com.msystech.clone_hospital.enums.RESPONSE_TYPE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidationAspect {

    RESPONSE_TYPE resType() default RESPONSE_TYPE.JSON;
    String movePage() default "/error";

    String[] stringIds() default {};
    String[] stringTitles() default{};

    String[] numberIds() default {};
    String[] numberTitles() default {};

    String[] digitIds() default {};
    String[] digitTitles() default {};
}

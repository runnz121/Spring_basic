package com.msystech.clone_hospital.aop;


import com.msystech.clone_hospital.enums.RESPONSE_TYPE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//retention 어노테이션으로 어느시점까지메모리를 가저갈것인가
@Target(ElementType.METHOD)//어노테이션 적용 위치
public @interface MemberNotLoginAspect {
    RESPONSE_TYPE resType() default RESPONSE_TYPE.JSON;
    String movePage() default "/";
}

package com.msystech.clone_hospital.aop;


import com.msystech.clone_hospital.enums.RESPONSE_TYPE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//retention 어노테이션으로 어느시점까지메모리를 가저갈것인가
@Target(ElementType.METHOD)//어노테이션 적용 위치
public @interface MemberLoginAspect {//커스텀 어노테이션 생성하기 위한 어노테이션 인터페이스 작성
    boolean isGradeAdmin() default true;
    boolean isGradeSaler() default false;
    boolean isGradeUser() default false;
    RESPONSE_TYPE resType() default RESPONSE_TYPE.JSON; //library 필요
    String loginPage() default "/biz/login";



}

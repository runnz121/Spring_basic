package com.msystech.clone_hospital.aop;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.msystech.clone_hospital.config.MemberSession;
import com.msystech.clone_hospital.enums.RESPONSE_TYPE;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@EnableAspectJAutoProxy //proxy방식으로 Aspect사용
@Aspect//build.gradle에 implements 추가
@Component
public class AspectImpl {
    @Around("@annotation(com.msystech.clone_hospital.aop.MemberLoginAspect)")         //Around advice쓸려면 ProceedingJoinPoint 호출해야됨
                                                                                      //@Around는 대상 객체 메서드 실행전과 실행후 적용되며, jointpopint.proceed()를 만나면 이 aop가 호출되어진다 / https://yookeun.github.io/java/2014/10/01/spring-aop/
    public Object MemberLoginAspect(ProceedingJoinPoint joinPoint) throws Throwable { //Around advice가 가로채는 호출을 넘겨주는 객체(ProceedingJoinPoint) 반환되는 object에는 메소드 실행 후 결과값이 반환// proceedingjoinpoint :  https://developer-joe.tistory.com/221
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();//https://offbyone.tistory.com/144 서블릿 객체를 직접 얻기위한 전체 객체 얻음
        //상위 작성된 서블릿 코드얻는 객체에서 다음의 속성값들을 얻어 올 수 있음
        HttpServletRequest request = attributes.getRequest();//request 요청은 사용하는 함수의 파라미터값을 가져옴
        HttpServletResponse response = attributes.getResponse();//response 요청은 사용하는 함수가 반환하는 리턴값을 가져옴

        //현재의 메소드를 가져온다(읽어온다)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//methodsignautre : 메소드의 이름과 파라미터를 말한다 https://ijbgo.tistory.com/19
        Method method = signature.getMethod();//지정한 signature에서 method를 가저옴

        //어노테이션 메소드를 가져온다(읽어온다)
        MemberLoginAspect validator = method.getAnnotation(MemberLoginAspect.class);//해당 메소드에 커스텀 어노테이션으로 접근
        RESPONSE_TYPE resType = validator.resType();  //json타입으로 반환한다고 지정해놓음
        String loginPage = validator.loginPage(); //login page로의 반환

        boolean isLogon = MemberSession.isLogged(request);//islogon은 참 불 반환
        String path = (new UrlPathHelper()).getPathWithinApplication(request); //uri의 뒷부분만 가져온다     //https://stackoverflow.com/questions/4278083/how-to-get-request-uri-without-context-path
        //https://homesi.tistory.com/entry/getContextPath-%EC%99%80-getRequestURI
        if (isLogon) {

            if (validator.isGradeAdmin() && MemberSession.isAdmin(request)) {
                return joinPoint.proceed();                 //@Around 에서 joinPoint.proceed()전과 후로 메소드를 나눈다 jointpoint.proceed()로 위에 정의한 aop를 호출한다(invoke)
            } else if (validator.isGradeSaler() && MemberSession.isSalesMan(request)) {
                return joinPoint.proceed();
            } else if (validator.isGradeUser() && MemberSession.isHospitalUser(request)) {
                return joinPoint.proceed();
            } else {
                return sendResponseMessage(response, resType, "페이지를 읽을 수 있는 권한이 없습니다. 다른 계정으로 로그인 후 이용하세요", request.getContextPath() + loginPage); // 프로젝트 path만 얻어온다  getPathWithinApplication(request) 와 비교
            }
        } else {
            return sendResponseMessage(response, resType, "로그인 후 이용할 수 있습니다.", request.getContextPath() + loginPage);
        }
    }

    @Around("@annotation(com.msystech.clone_hospital.aop.MemberLoginAspect)")
    public Object MemberNotLoginAspect(ProceedingJoinPoint jointPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        MethodSignature signature = (MethodSignature) jointPoint.getSignature();
        Method method = signature.getMethod();
        MemberNotLoginAspect validator = method.getAnnotation(MemberNotLoginAspect.class);
        RESPONSE_TYPE resType = validator.resType();
        String movePage = validator.movePage();

        boolean isLogon = MemberSession.isLogged(request);
        if (isLogon) {
            if (resType == RESPONSE_TYPE.JSON) {
                return sendResponseMessage(response, resType, "이미 로그인된 상태입니다. 로그아웃 후 진행하세요", request.getContextPath() + movePage);
            } else {
                return sendResponseMessage(response, resType, null, request.getContextPath() + movePage);
            }
        } else {
            return jointPoint.proceed();
        }
    }

    @Around("@annotation(com.msystech.clone_hospital.aop.ValidationAspect)")
    public Object ValidationAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ValidationAspect validator = method.getAnnotation(ValidationAspect.class);
        RESPONSE_TYPE resType = validator.resType();
        String movePage = validator.movePage();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        JSONObject requestJson = null;
        Object[] requestParams = joinPoint.getArgs(); //aop가 적용된 메소드에 전달된 인자(매개변수)를 받아내여 requestparams 배열로 전달  https://heekim0719.tistory.com/141
        //https://devofhwb.tistory.com/16
        if (requestParams != null && requestParams.length > 0) {

            for (Object object : requestParams) {
                if (JSONObject.class.equals(object.getClass())) { //object.getClass() 객체가 속하는 클래스의 정보를 알아냄 (requestparam에 담긴 객체의 클래스가 jsonobject클래스와 같은지)
                    requestJson = (JSONObject) object;
                    break;
                }
            }
        }

        if (request != null) {
            String message = JSONValidation.validBlank(requestJson, validator.stringIds(), validator.stringTitles()); //message = 조사 을/를 을 반환함
            if (message != null) {
                return sendResponseMessage(response, resType, message + "작성하세요", request.getContextPath() + movePage);

            } else if (validator.numberIds() != null && (message = JSONValidation.validNumber(requestJson, validator.numberIds(), validator.numberTitles())) != null) {
                return sendResponseMessage(response, resType, message + " 숫자만 작성하세요", request.getContextPath() + movePage);

            } else if (validator.digitIds() != null && (message = JSONValidation.validDigitNumber(requestJson, validator.digitIds(), validator.digitTitles())) != null) {
                return sendResponseMessage(response, resType, message + " 숫자만 작성하세요", request.getContextPath() + movePage);
            }
        }

        return joinPoint.proceed();
    }



    @Around("@annotation(com.msystech.clone_hospital.aop.DataTablesPagingAspect)")
    public Object DataTAblesPagingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        JSONObject requestJson = null;
        Object[] requestParams = joinPoint.getArgs();
        if (requestParams != null && requestParams.length > 0) {
            for (Object object : requestParams) {
                if (JSONObject.class.equals(object.getClass())) {
                    requestJson = (JSONObject) object;
                    break;
                }
            }
        }

        if (requestJSon != null) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            DataTablesPagingAspect dataTable = method.getAnnotation(DataTablesPagingAspect.class);

            String dbType = dataTable.dbtype();
            if ("mysql".equalsIgnoreCase(dbType)) {
                requestJson.put("iDisplayStart", requestJson.getIntValue("start"));// requestJson.put : 라이브러리 해당값을 map으로 반환 // requestJson.getIntValue 를 숫자로 반환
                requestJson.put("iDisplayEnd", requestJson.getIntValue("length"));
            } else {
                requestJson.put("iDisplayStart", requestJson.getIntValue("start"));
                requestJson.put("iDisplayEnd", requestJSon.getIntValue("start") + requestJson.getIntValue("length"));
            }

            JSONArray orders = requestJson, getJSONArray ("order");
            if (orders != null) {
                JSONObject orderInfo = orders.getJSONObject(0);
                int columnNumber = orderInfo.getIntValue("Column");
                String sortValue = orderInfo.getString("dir");

                JSONArray columns = requestJson.getJSONArray("columns");
                if (columns != null && columns.size() > columnNumber) {
                    JSONObject columnInfo = columns.getJSONObject(columnNumber);
                    String columnName = columnInfo.getString("name");

                    requestJson.put("SORT", columnName + " " + sortValue);
                    requestJson.put("SORT_COLUMN", columnName);
                    requestJson.put("SORT_DIR", sortValue);
                    requestJson.put("SORT_NUM", columnNumber);
                }
            }
        }
        return joinPoint.proceed();
    }



        private Object sendResponseMessage (HttpServletResponse response, RESPONSE_TYPE resType, String message, String
        movePage) throws Exception {
            if (resType == RESPONSE_TYPE.JSON) {
                JSONResMessage resJson = new JSONResMessage();
                resJson.setStatusAndMessage(JSONResMessage.FAIL, message);
                return resJson.getResponseMessage();
            } else if (resType == RESPONSE_TYPE.HTML) {
                response.setContentType("text/html; charset=UTF-8");//브라우저에게 utf-8로 사용하라는 명령 https://cbw1030.tistory.com/62
                PrintWriter out = response.getWriter(); //이 형식 그대로 응답을 내보낼 응답을 내보낼 출력 스트림을 얻는다 http://blog.naver.com/PostView.nhn?blogId=min_sub&logNo=80069919296&parentCategoryNo=20&viewDate=&currentPage=1&listtype=0
                out.println(HttpServletResponseUtil.getAlertMEssage(message)); //out.println()형식으로 괄호 안의 내용을 스트림에 텍스트를 기록한다
            } else if (resType == RESPONSE_TYPE.MOVE) {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println(HttpSerlvertResponseUtil.getAlertMessageMove(null, movePage));
                return null;
            } else return null;
        }
    }
}

package com.msystech.clone_hospital.config;

import com.msystech.clone_hospital.business.BizCodeset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MemberSession{//SessionAb는 라이브러리
                                                                                              //-->login을 구현한 MemberController 에서 담을 객체 설정
    private static final String SESSION_MEMBER_USERID           ="MEMBER_USERID";             //-->session 지정           //session은 내가 정의 할 수 있다. 즉 http통신의 커다란 흐름속에 session이라는 이름으로 계속 돌고 있는데,
    private static final String SESSION_MEMBER_NAME           ="MEMBER_NAME";                                            //내가 session을 변수처럼 지정을 해주어서 그것을 서로다른 컨트롤이나 서비스로 보낼때 이용 할 수 있는 개념
    private static final String SESSION_MEMBER_DOMAIN           ="SVCE_DOMAIN";                                          //http 통신 -> 커다란 강, session 그 위를 떠다니는 이름지어지있는 배
    private static final String SESSION_MEMBER_GRADE           ="MEMBER_GRADE";

    private static final String MEMBER_GRADE_ADMIN           = BizCodeset.AUTH_GRADES_ADMIN;
    private static final String MEMBER_GRADE_SALES           =BizCodeset.AUTH_GRADES_SALES;
    private static final String MEMBER_GRADE_USER           =BizCodeset.AUTH_GRADES_HOSPITAL;


    //http session : https://gbsb.tistory.com/81


    public static boolean isLogged(HttpServletRequest request) {        //httpservletreuqest에서 http 요청시 전송한 값, 쿠키, 헤더, 클라이언트 정보등을 가져오는데 이게 request http://www.devkuma.com/books/pages/1190
        return getMemberId(request) !=null;                             //getMemeberId 와 동일한 파라미터 받음
    }

    public static void setMemberId(HttpSession session, String value) {
        session.setAttribute(SESSION_MEMBER_USERID, value);             //session.setAttribute(이름, 값) : 파라미터 이름으로 value 값을 할당(바인딩)한다 https://hyeonstorage.tistory.com/125
    }

    public static String getMemberId(HttpServletRequest request) {
        HttpSession session         = request.getSession();             //request.getSession() 는 session이 생성된 경우 session리턴 그렇지 않으면 새롭게 session 생성하여 리턴  https://m.blog.naver.com/PostView.nhn?blogId=wonminst&logNo=90095292366&proxyReferer=https:%2F%2Fwww.google.com%2F
        return getStringSessionValue(session, SESSION_MEMBER_USERID);   //getStringSessionValue는 라이브러리 key로 바인딩된 객체를돌려주고 없으면 null (여기서 key는 다른것들이 올수 있어 그 값으로 매칭된것을 반환) =>  session.getAttribute(key);
    }

    public static String getMemberGrade(HttpServletRequest request) {
        HttpSession session         = request.getSession();
        return getStringSessionValue(session, SESSION_MEMBER_GRADE);
    }

    public static boolean isAdmin(HttpServletRequest request) {
        String grade                = getMemberGrade(request);
        return MEMBER_GRADE_ADMIN.equals(grade);
    }

    public static boolean isSalesMan(HttpServletRequest request) {
        String grade                = getMemberGrade(request);
        return MEMBER_GRADE_SALES.equals(grade);
    }

    public static boolean isHospitalUser(HttpServletRequest request) {
        String grade                = getMemberGrade(request);
        return MEMBER_GRADE_ADMIN.equals(grade);
    }

    public static void setMemberInfo(HttpServletRequest request, String grade, String memberId, String memberName, String subdomain){
        HttpSession session         = request.getSession();                 //session 생성
        session.setAttribute(SESSION_MEMBER_USERID, memberId);              //key, value값 바인딩
        session.setAttribute(SESSION_MEMBER_NAME, memberName);
        session.setAttribute(SESSION_MEMBER_GRADE, grade);
        session.setAttribute(SESSION_MEMBER_DOMAIN, subdomain);

    }

    public static void setMemberName(HttpServletRequest request, String value){
        HttpSession session         = request.getSession();
        setMemberName(session, value);
    }

    public static void setMemberName(HttpSession session, String value){
        session.setAttribute(SESSION_MEMBER_NAME, value);
    }

    public static String getMemberName(HttpServletRequest reqeust) {
        return getStringSessionValue(reqeust.getSession(), SESSION_MEMBER_NAME); //getStringSessionValue 라이브러리 위와 동일
    }

    public static String getSessionId(HttpServletRequest request) {
        HttpSession session         = request.getSession();
        return (session == null) ? null : session.getId();
    }

    //라이브러리 매서드 여기서 구현
    protected static String getStringSessionValue(HttpSession session, String key){
        if(session ==null) {
            return null;
        }else{
            Object obj = session.getAttribute(key);
            return obj == null ? null :(String)obj;
        }
    }

}

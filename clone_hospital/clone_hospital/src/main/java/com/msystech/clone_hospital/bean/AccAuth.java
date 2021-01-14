package com.msystech.clone_hospital.bean;

import java.io.Serializable;

public class AccAuth implements Serializable { //DB에서 읽어온 내용을 담는 객체(도메인 객치) 는 직렬화한다 https://developerhjg.tistory.com/34
    private static final long serialVersionUID = -1L; //추후 클래스 변경해도 역직렬화에 상관 없음

    private String accUserid;

    private String accPasswd;

    private String accName;

    private String accBelong;

    private String accTel;

    private String authType;

    private String accUseyn;

    private String regdt;

    private String modifydt;

    public AccAuth() {

    }

    public String getAccUserid() {
        return accUserid;
    }

    public void setAccUserid(String accUserid) {
        this.accUserid = accUserid;
    }

    public String getAccPasswd() {
        return accPasswd;
    }

    public String getRegdt() {
        return regdt;

    }

    public void setRegdt(String regdt) {
        this.regdt = regdt;
    }


}

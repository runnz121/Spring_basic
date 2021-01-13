package com.msystech.clone_hospital.business;

public class BizCodeset {


    public static final boolean ISSUE_PASSWD_ENC    =true;


    public static String AUTH_GRADES_ADMIN          ="1";
    public static String AUTH_GRADES_SALES          ="2";
    public static String AUTH_GRADES_HOSPITAL       ="3";


    public static String USE_YN_Y                   ="Y";
    public static String USE_YN_N                   ="N";
    public static String[][] USE_YN                 = {
            {"Yes", USE_YN_Y},
            {"No", USE_YN_N}
    };

    public static final String[][] VM_SIZES         = {
            {"10G", "10G"},
            {"20G", "20G"},
            {"30G", "30G"},
            {"40G", "40G"},
            {"50G", "50G"}
    };

    public static String[][]    SERVICE_TYPES       = {
            {"환자안전관리", "1"},
            {"병동환자관리", "2"},
            {"전자동의서", "3"},
            {"처방조회", "4"},
            {"블록체인", "5"}
    };

    public static String CONT_STATUS_A1 	= "A1";
    public static String CONT_STATUS_A2 	= "A2";
    public static String CONT_STATUS_A4 	= "A4";
    public static String CONT_STATUS_A5 	= "A5";
    public static String CONT_STATUS_A6 	= "A6";
    public static String CONT_STATUS_A7 	= "A7";
    public static String[][] CONT_STATUS 	= {
            {"가입및계약준비", CONT_STATUS_A1},
            {"계약후승인대기", CONT_STATUS_A2},
            {"가승인", CONT_STATUS_A4},
            {"승인", CONT_STATUS_A5},
            {"개통대기", CONT_STATUS_A6},
            {"정상", CONT_STATUS_A7}
    };

    /* 서비스 처리 신청 */
    public static String SVR_STATUS_REQ 		= "SR";
    /* 서비스 처리 시작 */
    public static String SVR_STATUS_START 		= "SS";
    /* 서비스 처리 체크 */
    public static String SVR_STATUS_CHECK 		= "SK";
    /* 서비스 처리 완료 */
    public static String SVR_STATUS_COMPLETE 	= "SC";
    /* 서비스 처리중 오류 */
    public static String SVR_STATUS_ERROR 		= "SE";

    public static String[][] CHARGE_PLAN 	= {
            {"1명~50명", "M50"},
            {"51명~100명", "M100"},
            {"101명이상", "M101"}
    };

    public static String[][] BLOCKCHAIN_WORKID 	= {
            {"환자차트인식", "MN001"},
            {"검체인식", "MN002"},
            {"처방확인", "MN003"},
            {"처방조회", "MN004"},
            {"경과기록지", "MN005"},
            {"Lab결과목록 조회", "MN006"},
            {"Lab결과 조회", "MN007"},
            {"동의서 환자 정보 조회", "MN008"},
            {"동의서 저장", "MN009"},
            {"액팅저장", "MN010"},
            {"액팅삭제", "MN011"},
            {"BST 업데이트", "MN012"}
    };

    public static String getCodeName(String[][] list, String value) {   //getCodeName 호출시
        int size            =list.length;
        for (int i= 0; i< size; i++) {
            if(list[i][1].equalsIgnoreCase(value)) {                    //대소문자 구분 없이 비교
                return list[i][0];                                      //i번째 값을 반환
            }
        }
        return null;
    }
    public static void main()   {                                       //왜 필요할까?


    }

}

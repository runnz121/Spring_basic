package com.msystech.clone_hospital.business;


import com.msystech.clone_hospital.aop.MemberLoginAspect;
import com.msystech.clone_hospital.config.MemberSession;
import com.msystech.clone_hospital.enums.RESPONSE_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/biz")
public class BIZ03Controller {
    private final org.slf4j.Logger LOGGER 			= org.slf4j.LoggerFactory.getLogger("TYPHOON_LOG");

    public static String FILE_SUB_DIR_FAQ   = "/FAQ";

    @Autowired
    private SolutionConfig solutionConfig;

    @Autowired
    private BIZ03Service biz03Service;

    @RequestMapping({"/BIZ03", "/BIZ0302"})
    public ModelAndView index(HttpServletRequest request) throws Exception {
        String path =(new UrlPathHelper()).getPathWithinApplication(request);
        if(path != null && path.endsWith("/BIZ03")) {
            path    =   path +"02";
        }
        ModelAndView mv = new ModelAndView(path);
        return mv;
    }

    @MemberLoginAspect(resType = RESPONSE_TYPE.MOVE)
    @RequestMapping({"/BIZ0302_I"})
    public ModelAndView memberShowPage(HttpServletRequest request) throws Exception {
        String path =(new UrlPathHelper()).getPathWithinApplication(request);
        if(path!=null && path.endsWith("/BIZ03")) {
            path    =path +"02";
        }
        ModelAndView mv = new ModelAndView(path);
        return mv;
    }

    @PostMapping("/BIZ0302_LA")
    public @ResponseBody JSON bIZ0302_LA(HttpServletRequest request, @ResponseBody JSONObject paramJson)
        JSONResMessage resJson  = null;

    try {
        resJson = this.biz03Service.bIZ0302_LA(paramJson);
    } catch(Exception e) {
        LOGGER.error("bIZ0302_LA", e);
        resJson = new JSONResMessage(JSONResMessage.FAIL, "요청작업 처리중 오류 발생");
    }

    return resJson.getResponseMessage();



    @MemberLoginAspect(resType = RESPONSE_TYPE.MOVE)
    @RequestMapping({"/BIZ0302_E"})
    public ModelAndView bIZ0302_E(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/biz/BIZ0302_E");
        JSONObject paramJson = JSonUtil.makeJSONObjectFromRequest(request);
        JSONObject resJson  = this.biz03Service.bIZ0302_E(paramJson, (request.getHeader(("X-FORWARDED-FOR") != null)? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr());
                                                                                        // 누가 요청한건지 알기 위해 https://www.zerocho.com/category/HTTP/post/5b611b9e33b4636aa8bb1fc4
        if(paramJson.containsKey("w_field")&&paramJson.getString("w_field").length() >0) {
            resJson.put("w_filed", paramJson.getString("w_filed"));
        }
        if(paramJson.containsKey("regdt") && paramJson.getSTring("regdt").length()>0) {
            resJson.put("regdt", paramJson.getString("regdt"));
        }

        mv.addObject("bean", resJson);
        return mv;

    }
    @MemberLoginAspect
    @PostMapping("/BIZ0302_DA")
    public @ResponseBody JSON bIZ0302_DA(HttpServletRequest request, @RequestBody JSONObject paramJSon) throws Exception {
        JSONResMessage resJson  =null;
        try{
            resJson =   this.biz03Service.bIZ0302_DA(paramJson, MemberSession.isAdmin(request))
        } catch(Exception e) {
            LOGGER.error("adm_01AL", e);
            resJson =   new JSONResMessage(JSONResMessage.FAIL, "요청작업 처리중 오류 발생");
        }
        return resJson.getResponseMessage();
    }

    @RequestMapping("/BIZ0302_PE")
    @MemberLoginAspect(resType = RESPONSE_TYPE.MOVE)
    public ModelAndView bIZ0302_PE(HttpServeletRequest request, HttpServletResponse response, @RequestParam(value="uid", required=false, defaultValue="0") String bbs_notice_uidT) throws Exception {
        int bbs_notice_uid  =   STRINGUtil.getParam(bbs_notice_uidT, 0);
        if(bbs_notice_uid <= 0) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out =   response.getWriter();//printwriter : 문자와(char)바이트 스트림 출력 가능
            out.println(HttpServletResponseUtil.getAlertMEssageBack("게시글번호가 존재하지 않는다"); //프레임워크
            return null;
        } else {
            ModelAndView mv = new ModelAndView("/biz/BIZ0302_PE");
            JSONObject bean = null;
            try {
                bean    =   this.biz03Service.bIZ0302_PE(bbs_notice_uid);
                mv.addObejct("bean", bean0);
            } catch(Exception e) {
                LOGGER.error("bIZ0302_PE", e);
            }
            return mv;
        }
    }

    @RequestMapping("/BIZ0302_FD");
    public void dn_BIZ0302_FD(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject paramJson    =   JsonUtil.makeJSONObjectFromRequest(request);

        String downloadName =   paramJson.getString("original");
        String webFilePath  =   paramJson.getSTring("filepath");
        String realDirPath  =   solutionConfig.getUploaddir();
        File dir    =   new File(realDirPath, FILE_SUB_DIR_FAQ);//파일 정보 https://m.blog.naver.com/PostView.nhn?blogId=heoguni&logNo=130169987115&proxyReferer=https:%2F%2Fwww.google.com%2F
        File file   =   new File(dir, webFilePath);
        try {
            FileDownloader.download(request, response, file, downloadName);
        } catch(FileNotFoundException e) {
            HttpServletResponseUtil.writeLayerAlertMessage(response, "요청하신 파일은 존재하지않습니다");
        } catch(Exception e) {
            HttpServletResponseUtil.writeLayerAlertMessage(response, "전송중 기타 오류 발생");
        }
    }

    @MemberLoginAspect
    @PostMapping("/BIZ0302_IA")
    public @ResponseBody JSON bIZ0302_IA(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject paramJson    =   JSONUtil.makeJSONObjectFromRequest(request);
        paramJson.put("read_cnt", 1);
        paramJson.put("read_cnt", 1);
        paramJson.put("writer_ip", (request.getHeader("X-FORWARDED-FOR") != null)? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr());
        paramJson.put("view_ip", paramJson.getString("writer_ip"));
        paramJson.put("acc_userid", MemberSession.getMemberId(request));

        JSONResMessage resJson 		= null;

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;//파일 업로드를 위한 객체 생성 https://caileb.tistory.com/152

        try{
            String uuid =   System.currentTimeMillis()  +   "";
            new FileUploader(this.solutionConfig, paramJson) { //익명클래스 생성 후 상속받고 객체 생성 https://yookeun.github.io/java/2017/01/24/java-anonymousclass/
                @Override
                protected String getSaveWebDirRootPath() {
                    return FILE_SUB_DIR_FAQ;
                }

                @Override
                protected String[] getDeleteImageFiles() {
                    return null;
                }

                @Override
                protected String[] getOldImageFiles() {
                    return null;
                }

                @Override
                protected String[] getImageFiles() {
                    return new String[] {"file"};
                }

                @Override
                protected String[] getDbFieldes() {
                    return new String[] {"att_file"};
                }

                @Override
                protected String[] getDbOriFieldes() {
                    return new String[] {"att_file_originnm"};
                }
            }.upload(multipartRequest, response, uuid);

            resJson =   this.biz03Service.bIZ0302_IA(paramJson);
        } catch(Exception e) {
            LOGGER.error("bIZ0302_IA", e);
            resJson =   new JSONResMessage(JSONResMessage.FAIL, "요청작업처리중 에러");
        }

        return resJson.getResponseMessage();
    }

    @MemberLoginAspect
    @PostMapping("/BIZ0302_EA")
    public @ResponseBody JSON bIZ0302_EA(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        JSONObject paramJson 		= JSONUtil.makeJSONObjectFromRequest(request);
        paramJson.put("writer_ip", (request.getHeader("X-FORWARDED-FOR") != null)? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr());
        paramJson.put("view_ip", paramJson.getString("writer_ip"));
        paramJson.put("acc_userid", MemberSession.getMemberId(request));

        JSONResMessage resJson 		= null;

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        try {
            String uuid 			= System.currentTimeMillis() + "";
            new FileUploader(this.solutionConfig, paramJson) {
                @Override
                protected String getSaveWebDirRootPath() {
                    return FILE_SUB_DIR_FAQ;
                }

                @Override
                protected String[] getDeleteImageFiles() {
                    return new String[] {"del_file"};
                }

                @Override
                protected String[] getOldImageFiles() {
                    return new String[] {"old_file"};
                }

                @Override
                protected String[] getImageFiles() {
                    return new String[] {"file"};
                }

                @Override
                protected String[] getDbFieldes() {
                    return new String[] {"att_file"};
                }

                @Override
                protected String[] getDbOriFieldes() {
                    return new String[] {"att_file_originnm"};
                }
            }.upload(multipartRequest, response, uuid);

            resJson 				= this.biz03Service.bIZ0302_EA(paramJson);
        } catch(Exception e) {
            LOGGER.error("bIZ0302_IA", e);
            resJson 				= new JSONResMessage(JSONResMessage.FAIL, "요청작업 처리중 오류가 발생했습니다");
        }

        return resJson.getResponseMessage();
    }

}

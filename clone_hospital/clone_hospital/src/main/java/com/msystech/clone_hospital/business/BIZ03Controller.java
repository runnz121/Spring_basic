package com.msystech.clone_hospital.business;


import com.msystech.clone_hospital.aop.MemberLoginAspect;
import com.msystech.clone_hospital.config.MemberSession;
import com.msystech.clone_hospital.enums.RESPONSE_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        }


    }

}

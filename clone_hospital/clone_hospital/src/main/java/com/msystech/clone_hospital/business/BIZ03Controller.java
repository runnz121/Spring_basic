package com.msystech.clone_hospital.business;


import com.msystech.clone_hospital.aop.MemberLoginAspect;
import com.msystech.clone_hospital.enums.RESPONSE_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

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

}

@MemberLoginAspect(resType = RESPONSE_TYPE.MOVE)
@RequestMapping({"/BIZ0302_E"})
public ModelAndView bIZ0302_E(HttpServletRequest request) throws Exception {
    ModelAndView mv = new ModelAndView("/biz/BIZ0302_E");
    
}

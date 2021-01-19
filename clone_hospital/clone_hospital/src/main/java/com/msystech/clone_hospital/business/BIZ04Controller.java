package com.msystech.clone_hospital.business;


import com.msystech.clone_hospital.aop.MemberLoginAspect;
import com.msystech.clone_hospital.enums.RESPONSE_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/biz")
public class BIZ04Controller {
    private final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("TYPHOON_LOG");

    public static final String SAVE_DIR_CONTRACT    =   "/CONTRACT";

    @Autowired
    private SolutionConfig solutionConfig;

    @Autowired
    private BIZ04Service biz04Service;

    @Autowired
    private BIZ05Service biz05Service;

    @Autowired
    private BIZ06Service biz06Service;

    public ModelAndView index(HttpServletRequest request) throws Exception { // 자바 서블렛 https://mangkyu.tistory.com/14
        String path =   (new UrlPathHelper()).getPathWithinApplication(request);
        if(path != null && path.endsWith("/BIZ04")) {
            path    =   path +"01";
        }
        ModelAndView mv = new ModelAndView(path);
        return mv;
    }

    @MemberLoginAspect(resType = RESPONSE_TYPE.MOVE, isGradeAdmin = false, isGradeSaler = true)
    @GetMapping({"/BIZ04", "/BIZ0401"})
    public ModelAndView bIZ0401(HttpServletRequest request) throws Exception {
        return index(request);
    }

    @MemberLoginAspect(resType =RESPONSE_TYPE.MOVE, isGradeAdmin = false, isGradeSaler = false, isGradeUser = true)
    @GetMapping({"/BIZ0402"})
    public ModelAndView bIZ0402(HttpServletRequest request) throws Exception {
        return index(request);
    }

    @MemberLoginAspect(resType = RESPONSE_TYPE.MOVE)
    @GetMapping({"/BIZ0405"})
    public ModelAndView bIZ0405(HttpServletRequest request) throws Exception {
        return index(request);
    }

    @MemberLoginAspect(isGradeAdmin = false, isGradeSaler = true)
    @PostMapping("/BIZ0401_F")
    public @ResponseBody JSon bIZ0401_F(HttpServletRequest request, @RequestBody JSONObject paramJson) throws Exception {
        JSONResMessage resJson  =   new JSONResMEssage(JSONResMessage.SUCCESS);
        resJSon.put("codes_hospi_field", BizCodeset.SERVICE_TYPES);
        resJSon.put("codes_charge_plan", BizCodeset.CHARGE_PLAN);
        resJson.put("codes_status", BizCodeset.CONT_STATUS);

        return resJson.getREsponseMessage();
    }



}

package com.msystech.clone_hospital.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/biz")
public class BIZ01Controller {
    private final org.slf4j.Logger LOGGER   = org.slf4j.LoggerFactory.getLogger("TYPHOON_LOG"); //로그를 남기기 위한 객체 생성 https://enai.tistory.com/36


    @Autowired
    private SolutionConfig solutionConfig;

    @Autowired
    private BIZ01Service biz01Service;

    @GetMapping({"","/","/index","/BIZ01","/BIZ0101"})
    public ModelAndView index(HttpServletRequest request) { // 모델로 반환 https://hongku.tistory.com/116
        ModelAndView mv = new ModelAndView("/biz/BIZ0101");
        return mv;
    }
}

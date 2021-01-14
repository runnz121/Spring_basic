package com.msystech.clone_hospital.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/biz")
public class BIZ02Controller {
    private final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("TYPHOON_LOGGER");

    @Autowired
    private SolutionConfig solutionConfig;

    @Autowired
    private BIZ02Service biz02Service;

    @RequestMapping({"/BIZ02", "/BIZ0201", "/BIZ0202", "/BIZ0203"})
    public ModelAndView memberShowPage(HttpServletRequest request) throws Exception {
        String path = (new UrlPathHelper()).getPathWithinApplication(request);
        if(path !=null && path.endsWith("/BIZ02")){
            path = path +"01";
        }
        ModelAndView mv = new ModelAndView(path);
        return mv;
    }
}

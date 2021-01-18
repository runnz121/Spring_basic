package Spring.SpringCRUD.web;


import Spring.SpringCRUD.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //scope 이 request 인데 http request 자체가 없기 때문에 scope life cycle에 적용되지 않음

    @RequestMapping("log-demo")
    @ResponseBody//문자를 바로 반환
    public String logDemo(HttpServletRequest request) throws InterruptedException {

        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerProvider.getObject(); //이 시점에 mylogger 생성

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");

     //   Thread.sleep(3000);
        logDemoService.logic("testID");
        return "ok";
    }
}

package io.mine.ft.train.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.mine.ft.train.conf.Reference;
import io.mine.ft.train.service.SayHelloService;

/**
 * 项目心跳监测接口
 */
@Controller
public class ZBJCheckController {
    
    @Reference
    private SayHelloService sayHelloService;

    @RequestMapping(value = "/zbjcheck", method = RequestMethod.GET)
    @ResponseBody
    public void zbjcheck() {
        
        String sayHello = sayHelloService.sayHello("hello");
        System.out.println(sayHello);
      
    }
}

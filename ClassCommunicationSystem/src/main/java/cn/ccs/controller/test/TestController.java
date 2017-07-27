package cn.ccs.controller.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lixingjie@zdsoft.cn
 * @create 2017-07-26 11:26
 **/

@Controller
public class TestController {


    @RequestMapping(value = "/test")
    public String indexView(){
        return "demo";
    }
}

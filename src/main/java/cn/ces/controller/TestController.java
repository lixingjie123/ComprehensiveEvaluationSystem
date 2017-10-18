package cn.ces.controller;
import cn.ces.entity.Courseware;
import cn.ces.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"test"})
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/test")
    public String Test(ModelMap map){
        Courseware courseware = testService.SelectAll(14);
        map.addAttribute("test",courseware);
        return "demo";
    }
}

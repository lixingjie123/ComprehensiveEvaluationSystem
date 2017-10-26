package cn.ces.controller;
import cn.ces.entity.Department;
import cn.ces.entity.Test;
import cn.ces.service.TestService;
import com.mybatis.enhance.store.manager.common.BaseMysqlCRUDManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"Test"})
public class TestController {

    private final TestService testService;
    private final BaseMysqlCRUDManager baseMysqlCRUDManager;

    @Autowired
    public TestController(TestService testService, BaseMysqlCRUDManager baseMysqlCRUDManager) {
        this.testService = testService;
        this.baseMysqlCRUDManager = baseMysqlCRUDManager;
    }

  /*  @GetMapping(value = "/test")
    public String Test(ModelMap map){
        Courseware courseware = testService.selectByPrimaryKey(14);
        map.addAttribute("test",courseware);
        return "demo";
    }
*/
  /*  @GetMapping(value = "/test2")
    public String Test2(ModelMap map){
        Test test = testService.selectAll();
        map.addAttribute("test",test);
        return "demo";
    }
    */

    @GetMapping(value = "/test2")
    public String Test2(ModelMap map){
       Department departments = testService.selectDeptAll();
        map.addAttribute("tests",departments);
        return "demo";
    }
}

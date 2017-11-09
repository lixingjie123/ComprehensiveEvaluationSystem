package cn.ces.controller;

import cn.ces.entity.Class;
import cn.ces.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-11-08
 * Time: 11:08
 */

@Controller
public class ClassController {

    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping(value = "/selectPageByClname")
    @ResponseBody
    public Map<String ,Object> selectPageByClname(int offset, int limit, String clname){
        String cn = "%%";
        if (!clname.equals(null)&&!clname.equals("")&&!clname.equals("null")){
            cn = "%"+clname+"%";
        }
        return classService.selectPageByClname(cn,offset,limit);
    }

    @GetMapping(value = "/updateStatus",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updateStatus(Integer clid){
        return classService.updateClssOfFettle(clid);
    }

}

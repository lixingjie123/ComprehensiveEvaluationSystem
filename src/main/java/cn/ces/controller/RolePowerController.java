package cn.ces.controller;

import cn.ces.entity.Rolepower;
import cn.ces.service.RolePowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RolePowerController {

    private final RolePowerService rolePowerService;

    @Autowired
    public RolePowerController(RolePowerService rolePowerService) {
        this.rolePowerService = rolePowerService;
    }

    //新增role power
    @PostMapping(value = "/saveRolePower", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String saveRolePower(Rolepower rolepower){
        String msg = "添加失败";
        if (rolePowerService.insterRolePower(rolepower)) {
            msg = "添加成功";
        }
        return msg;
    }

    //删除role power
    @PostMapping(value = "/delRPbyPid", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delRPbyPid(int pid){
        String msg = "删除失败";
        if(rolePowerService.delRPbyPid(pid)){
            msg = "删除成功";
        }
        return msg;
    }
}

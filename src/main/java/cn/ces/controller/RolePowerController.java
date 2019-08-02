package cn.ces.controller;

import cn.ces.entity.Rolepower;
import cn.ces.service.RolePowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;
import java.util.List;

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

    //更新数据
    @PostMapping(value = "/updataRolePower", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updataRolePower(int rid, int[] pids){
        String res = "修改失败";
        for(int i = 0; i < pids.length; i++){
//            if(pids[i] >= 0){
//                //选中的
//                if(!rolePowerService.searchRid(pids[i])){
//                    Rolepower rolepower = new Rolepower();
//                    rolepower.setPid(pids[i]);
//                    rolepower.setRid(rid);
//                    rolePowerService.insterRolePower(rolepower);
//                }
//            }else{
//                //未选中的
//                if(rolePowerService.searchRid(pids[i])){
//                    rolePowerService.delRPbyRid(rid,pids[i]);
//                }
//            }
        }
        return res;
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

    //根据pid查找rid
    @PostMapping(value = "/selectRidByPid", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public List<Rolepower> selectRidByPid(int rid){
        List<Rolepower> list = rolePowerService.selectPidByRid(rid);
        return list;
    }

}

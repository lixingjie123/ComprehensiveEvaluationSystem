package cn.ces.service;

import cn.ces.dao.RolePowerDao;
import cn.ces.entity.Rolepower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePowerService {

    @Autowired
    private RolePowerDao rolePowerDao;

    //新增rolePower
    public Boolean insterRolePower(Rolepower rolepower){
        Boolean res = false;
        if(rolePowerDao.insterpoaerrole(rolepower.getRid(), rolepower.getPid())>0){
            res = true;
        }
        return res;
    }

    //删除rolePower
    public Boolean delRPbyPid(int pid){
        Boolean res = false;
        if(rolePowerDao.delRPbyPid(pid)>0){
            res = true;
        }
        return res;
    }
}

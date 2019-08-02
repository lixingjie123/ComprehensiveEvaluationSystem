package cn.ces.service;

import cn.ces.dao.RolePowerDao;
import cn.ces.entity.Rolepower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //根据pid删除rolePower
    public Boolean delRPbyPid(int pid){
        Boolean res = false;
        if(rolePowerDao.delRPbyPid(pid)>0){
            res = true;
        }
        return res;
    }

    //根据rid删除rolePower
    public Boolean delRPbyRid(int rid,int pid){
        Boolean res = false;
        if(rolePowerDao.delRP(rid,pid)>0)
            res = true;
        return res;
    }

    //根据pid获取rid列表
    public List<Rolepower> selectPidByRid(int rid){
        List<Rolepower> list;
        list = rolePowerDao.selectRidByPid(rid);
        return list;
    }

    //根据pid查找rid
    public Boolean searchRid(int pid){
        Boolean res = false;
        if(rolePowerDao.searchRid(pid)>0)
            res = true;
        return res;
    }

}

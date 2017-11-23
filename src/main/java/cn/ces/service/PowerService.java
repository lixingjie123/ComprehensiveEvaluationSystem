

package cn.ces.service;

import cn.ces.dao.PowerDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Power;
import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PowerService {
    @Autowired
    private PowerDao pd;

    //模糊查询菜单
    public List<Power> selectpower(String pname){
    	return pd.selectpower(pname);
    }
    public List<Power> selectpowertree(){
    	return pd.selectpowertree();
    }
    //插入菜单
    public Boolean insterpower(Power power){
    	Boolean b = false;
    	if(pd.insterpower(power)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    //删除菜单
    public Boolean delectpower(int pid){
    	Boolean b = false;
    	if(pd.delectpowerbyid(pid)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    //通过id查询菜单
    public Power selectposerbyid(int pid){
    	return pd.selectpowerbyid(pid);
    }
    //修改菜单
    public Boolean updatapower(Power p){
    	Boolean b = false;
    	if(pd.updatapower(p)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }

}

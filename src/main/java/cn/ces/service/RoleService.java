

package cn.ces.service;

import cn.ces.dao.PowerDao;
import cn.ces.dao.RoleDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Power;
import cn.ces.entity.Role;
import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RoleService {
    @Autowired
    private RoleDao rd;

    public Map<String,Object> selectallrole(int pageIndex, int pageSiz,String rname){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=rd.selectcount(rname);
        List<Role> rows=rd.selectrole(pageIndex,pageSiz,rname);
        
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
    public List<Power> selectpower(){
    	return rd.selectpoweroption();
    }
    public Boolean insterrole(Role role){
    	Boolean b = false;
    	if(rd.insterrole(role)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    public Boolean delectrole(int rid){
    	Boolean b = false;
    	if(rd.delectrolebyid(rid)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    public Power selectposerbyid(int pid){
    	return rd.selectpowerbyid(pid);
    }

}

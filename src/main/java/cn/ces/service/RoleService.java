

package cn.ces.service;

import cn.ces.dao.ClassDao;
import cn.ces.dao.DepartmentDao;
import cn.ces.dao.LeadersDao;
import cn.ces.dao.RoleDao;
import cn.ces.dao.RolePowerDao;
import cn.ces.dao.StudentDao;
import cn.ces.dao.TeachersDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Power;
import cn.ces.entity.Role;
import cn.ces.entity.Rolepower;
import cn.ces.entity.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RoleService {
    private final RoleDao rd;
    private final RolePowerDao rpd;
    @Autowired
    public RoleService(RoleDao roleDao, RolePowerDao rolePowerDao) {
        this.rd = roleDao;
        this.rpd = rolePowerDao;

    }



    public Map<String,Object> selectallrole(int pageIndex, int pageSiz,String rname){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=rd.selectcount(rname);
        List<Role> rows=rd.selectrole(pageIndex,pageSiz,rname);
        
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
    public List<Rolepower> selectpower(Integer rid){
    	return rpd.selectpoweroption(rid);
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
    public Boolean delectpower(int rid){
    	Boolean b = false;
    	if(rpd.delectpowerbyid(rid)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    public Power selectposerbyid(int pid){
    	return rd.selectpowerbyid(pid);
    }
    public Role selectrolebyname(String rname){
    	return rd.selectrolebyname(rname);
    }
    public Boolean dispower(int[] powerid,int rid){
    	int p = 0;
    
  
        	try {
        		for(int i=1;i<powerid.length;i++){
				p=rpd.insterpoaerrole(rid,powerid[i]);
        		}
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());
			}

    			
    		
    		
    	
    	if(p>0){
    		return true;
    	}return false;
    }

}

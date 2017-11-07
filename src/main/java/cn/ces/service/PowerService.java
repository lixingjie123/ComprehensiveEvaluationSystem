

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

    public  Map<String,Object> selectallpower(int pageIndex, int pageSiz,String pname){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=pd.selectcount(pname);
        List<Power> rows=pd.selectpower(pageIndex,pageSiz,pname);
        for(int i=0;i<rows.size();i++){
        	Power p=pd.selectpowerbyid(rows.get(i).getFp_id());
        	if(p!=null){
        	rows.get(i).setFname(p.getPname());
        	}
        }
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
    public List<Power> selectpower(){
    	return pd.selectpoweroption();
    }
    public Boolean insterpower(Power power){
    	Boolean b = false;
    	if(pd.insterpower(power)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    public Boolean delectpower(int pid){
    	Boolean b = false;
    	if(pd.delectpowerbyid(pid)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    public Power selectposerbyid(int pid){
    	return pd.selectpowerbyid(pid);
    }

}

package cn.ces.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.CourseDao;
import cn.ces.dao.GiveCourseDao;
import cn.ces.dao.RoleDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Course;
import cn.ces.entity.GiveCourse;
import cn.ces.entity.Information;
import cn.ces.entity.Users;

@Service
public class GiveCourseService {


	private final GiveCourseDao givecoursedao;
	@Autowired
	public GiveCourseService(GiveCourseDao givecoursedao) {
		this.givecoursedao =  givecoursedao;
	
	}
	//展示开课
	public Map<String,Object> selectgivecourse(String cname,String uname,String clname,int pageIndex, int pageSiz) {
		Map<String,Object> result = new HashMap<String,Object>();
		int total=givecoursedao.selectcount();
		List<GiveCourse> rows=givecoursedao.selectgivecourse(cname,clname,uname,pageIndex, pageSiz);
		
		   result.put("total",total);
	        result.put("rows",rows);
		return result;
		
	}
	//保存开课
    public Boolean instercourse( Information information){
    	Boolean b = false;
    	if(givecoursedao.insterinformation(information)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
  //删除开课
    public Boolean delectgivecourse(int info_id){
    	Boolean b = false;
    	if(givecoursedao.delectgivecoursebyid(info_id)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }

}

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
import cn.ces.dao.TeachersDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.GiveCourse;
import cn.ces.entity.Teachers;
import cn.ces.entity.Users;

@Service
public class TeacherService {


	private final TeachersDao td;
	private final UsersDao ud;
	@Autowired
	public TeacherService(TeachersDao td,UsersDao ud) {
		this.td =  td;
		this.ud =  ud;
	
	}
	
	public List<Teachers> selectteacher() {
	
		
		List<Teachers> rows=td.selectTeachers();
		for(int i=0;i<rows.size();i++){
			Users u=ud.selectUserByUid(rows.get(i).getTid());
			if(u!=null){
			rows.get(i).setUname(u.getUname());}
		}
		
		   
		return rows;
		
	}

}

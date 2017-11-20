

package cn.ces.service;

import cn.ces.dao.CourseDao;
import cn.ces.entity.Course;
import cn.ces.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CourseService {
    private final CourseDao cd;
    
    @Autowired
    public CourseService(CourseDao cd) {
        this.cd = cd;
        

    }


   //分页查询课程
    public Map<String,Object> selectallcourse(int pageIndex, int pageSiz,String cname){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=cd.selectcount(cname);
        List<Course> rows=cd.selectcourse(pageIndex,pageSiz,cname);
        
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
    public List<Course> selectallcourse2(){
       
        List<Course> rows=cd.selectCourseAll();
        
     
        return rows;
    }
    //添加课程
    public Boolean instercourse(Course course){
    	Boolean b = false;
    	if(cd.instercourse(course)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    //删除课程
    public Boolean delectcourse(int rid){
    	Boolean b = false;
    	if(cd.delectcoursebyid(rid)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    
   //名字查询课程
    public Role selectrolebyname(String rname){
    	return cd.selectcoursebyname(rname);
    }
    
}

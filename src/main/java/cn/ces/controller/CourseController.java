package cn.ces.controller;

import cn.ces.entity.Course;
import cn.ces.entity.Power;
import cn.ces.entity.Role;
import cn.ces.entity.Rolepower;
import cn.ces.service.CourseService;
import cn.ces.service.PowerService;
import cn.ces.service.RoleService;
import cn.ces.tool.TreeNode;

import net.sf.json.JSONArray;
import cn.ces.tool.TreeNodeTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


@Controller
public class CourseController {

    private final CourseService courseService;
    

    @Autowired
    public CourseController( CourseService courseService) {
        this.courseService = courseService;
        
    }
      //模糊查询课程，并分页
    @GetMapping(value = "/selectcourse")
    @ResponseBody
    public Map<String,Object> selectcourse(int offset, int limit,String cname){
       	try {
            cname= URLDecoder.decode(cname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(cname);
    	String p="%%";
    	
      if(!cname.equals("null")){
    	 p="%"+cname+"%";} 
        return  courseService.selectallcourse(offset, limit,p);
    }
    
    //添加课程
    @PostMapping(value = "/seavecourse",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String seavecourse(Course course){
    	String msg;
    	
    
       if(courseService.instercourse(course)){
    	    msg = "添加课程成功";
       }else msg = "添加失败";

       
        return msg;
    }
    //删除课程
    @GetMapping(value = "/delectcourse",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delectcourse(int cid){
    	String msg; 
        if(courseService.delectcourse(cid)){
        	
     	    msg = "删除成功";
        }else msg = "删除失败";

         return msg;
    	
    	
    }
    //修改课程
    @PostMapping(value = "/updatacourase",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updatacouase(Course course){
    	String msg;
    	courseService.delectcourse(course.getCid());
    	
    	
       if(courseService.instercourse(course)){
    	    msg = "修改课程成功";
       }else msg = "修改失败";
      
   
        return msg;
    
    }


    
}

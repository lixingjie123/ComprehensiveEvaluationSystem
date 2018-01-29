package cn.ces.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import cn.ces.entity.Course;
import cn.ces.entity.GiveCourse;
import cn.ces.entity.Information;
import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ces.service.GiveCourseService;
import cn.ces.service.LoginService;

//开课管理
@Controller
public class GivecourseController {

	private final GiveCourseService gs;
	
	@Autowired
	public GivecourseController( GiveCourseService gs) {
		this.gs = gs;
	}
	//展示开课
	@GetMapping(value = "/showgivecourse")
    @ResponseBody 
    public Map<String, Object> showgivecourse(String cname,String uname,String clname,int offset, int limit){
		try {
			cname= URLDecoder.decode(cname,"UTF-8");
			clname= URLDecoder.decode(clname,"UTF-8");
			uname= URLDecoder.decode(uname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String c="%%";
		String u="%%";
		String cl="%%";
	    if(!"null".equals(cname)){
	    	 c="%"+cname+"%";} 
	    if(!"null".equals(uname)){
	    	 u="%"+uname+"%";} 
	    if(!"null".equals(clname)){
	    	 cl="%"+clname+"%";} 
		Map<String, Object> gclist=gs.selectgivecourse(c,cl,u,offset, limit);
		return  gclist;
	}
	//保存开课
	   @PostMapping(value = "/seavegivecourse",produces = "text/plain;charset=utf-8")
	    @ResponseBody
	    public String seavecourse(Information information){
	    	String msg;
	    	
	    
	       if(gs.instercourse(information)){
	    	    msg = "添加课程成功";
	       }else {
               msg = "添加失败";
           }

	       
	        return msg;
	    }
	   //删除开课
	    @GetMapping(value = "/delectgivecourse",produces = "text/plain;charset=utf-8")
	    @ResponseBody
	    public String delectgivecourse(int info_id){
	    	String msg; 
	        if(gs.delectgivecourse(info_id)){
	        	
	     	    msg = "删除成功";
	        }else {
				msg = "删除失败";
			}

	         return msg;
	    	
	    	
	    }//修改开课
	    @PostMapping(value = "/upgivecourse",produces = "text/plain;charset=utf-8")
	    @ResponseBody
	    public String upseavecourse(Information information){
	    	String msg;
	    System.out.println(information.getInfo_id());
	      gs.delectgivecourse(information.getInfo_id());
	       if(gs.instercourse(information)){
	    	    msg = "修改课程成功";
	       }else {
			   msg = "修改失败";
		   }

	       
	        return msg;
	    }
	
}

package cn.ces.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.GiveCourseDao;
import cn.ces.dao.LeadersDao;
import cn.ces.dao.QuestionDao;
import cn.ces.dao.ScoreDao;
import cn.ces.dao.StudentDao;
import cn.ces.dao.TeachersDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.GiveCourse;
import cn.ces.entity.Leaders;
import cn.ces.entity.Students;
import cn.ces.entity.Teachers;
import cn.ces.entity.Users;
@Service
public class EvaluationService {
	private final QuestionDao qd;
    private final UsersDao us;
    private final StudentDao sd;
    private final GiveCourseDao gd;
    private final ScoreDao scd;
    private final TeachersDao td;
    private final LeadersDao ld;
    
    
    @Autowired
    public EvaluationService(QuestionDao qd,UsersDao us,StudentDao sd,GiveCourseDao gd,ScoreDao scd,TeachersDao td,LeadersDao ld) {
        this.qd = qd;
        this.us = us;
        this.sd = sd;
        this.gd = gd;
        this.scd = scd;
        this.td = td;
        this.ld = ld;
        

    }
    public Map<String,Object> selectteacher(Integer uid,Integer qid,int pageIndex, int pageSiz){
    	Map<String,Object> result = new HashMap<String,Object>();
    	
    	Users u=us.selectUserByUid(uid);
    	
    	if(qd.selectquestiontype()!=null){
        if(u.getRid()==0){
        	Students su = sd.selectStudent(uid);
        	int total=gd.selectcount();
        	List<GiveCourse> gc = gd.selectgivecoursebycid(su.getClid(),pageIndex, pageSiz);
        	for(int i=0;i<gc.size();i++){
        	
        		if(scd.selectCoursebyid(uid, qid, gc.get(i).getTid())!=null){
        			gc.get(i).setType(0);
        		}else{
        			gc.get(i).setType(1);
        		}
        	}
        	 result.put("total",total);
 	        result.put("rows",gc);
        }
        if(u.getRid()==1){
        	if(qid==2){
        		Teachers tu=td.selectTeachersbyid(uid);
        		int total=td.selectCount();
        		List<Teachers> tl=td.selectTeachersByDeptid(tu.getDept_id());
        		for(int j=tl.size()-1;j>=0;j--){
        			if(tl.get(j).getTid().equals(uid)){
        				tl.remove(j);
        			}
        		}
        	  	for(int i=0;i<tl.size();i++){
            		if(scd.selectCoursebyid(uid, qid, tl.get(i).getTid())!=null){
            			tl.get(i).setType(0);
            		}else{
            			tl.get(i).setType(1);
            		}}
        		 result.put("total",total);
      	        result.put("rows",tl);
        	}else{
        		Teachers tu=td.selectTeachersbyid(uid);
        		Users ul=us.selectUserByUid(uid);
        		tu.setUname(ul.getUname());
        		List<Teachers> tl= new ArrayList<Teachers>();
        		tl.add(tu);
        		int total=td.selectCount();
        		if(scd.selectCoursebyid(uid, qid, tu.getTid())!=null){
        			tu.setType(0);
        		}else{
        			tu.setType(1);
        		}
        		result.put("total",total);
      	        result.put("rows",tl);
        	}
        	
        }
        if(u.getRid()==2){
        	Leaders leaders = ld.selectLeadersByid(uid);
        	int total=td.selectCount();
        	List<Teachers> tl=td.selectTeachersByDeptid(leaders.getDept_id());
     	  	for(int i=0;i<tl.size();i++){
        		if(scd.selectCoursebyid(uid, qid, tl.get(i).getTid())!=null){
        			tl.get(i).setType(0);
        		}else{
        			tl.get(i).setType(1);
        		}}
        	result.put("total",total);
  	        result.put("rows",tl);
        	
        	
        	
        }}
       
    	return result;
    }
}

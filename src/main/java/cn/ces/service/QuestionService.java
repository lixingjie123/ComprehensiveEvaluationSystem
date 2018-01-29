

package cn.ces.service;

import cn.ces.dao.CourseDao;
import cn.ces.dao.QuestionDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Course;
import cn.ces.entity.Questionnaire;
import cn.ces.entity.Role;
import cn.ces.entity.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class QuestionService {
    private final QuestionDao qd;
    private final UsersDao us;
    
    @Autowired
    public QuestionService(QuestionDao qd,UsersDao us) {
        this.qd = qd;
        this.us = us;
        

    }


   //分页查询问卷
    public Map<String,Object> selectallquestion(int pageIndex, int pageSiz,String qname){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=qd.selectcount(qname);
        List<Questionnaire> rows=qd.selectquestion(pageIndex,pageSiz,qname);
        
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
    //启用/禁用问卷
    public Boolean stopgivecourse(Integer qid){
    	Boolean b = false;
    	Questionnaire q = qd.selectQuestionnairebyid(qid);
    	if(q.getFettle()==1) {
            q.setFettle(0);
        } else {
            q.setFettle(1);
        }
    	if(qd.upfetter(q)>0){
    		b=true;
    	}else {
            b = false;
        }
    	return b ;
    }
    //修改问卷
    public Boolean upquestion(Integer qid,String qname){
    	Boolean b = false;
    	if(qd.upquestion(qid,qname)>0){
    		b=true;
    	}else {
            b = false;
        }
    	return b ;
    }
    //list问卷
    public List<Questionnaire> selectquestion(Integer uid){
    	Users u=us.selectUserByUid(uid);
    	List<Questionnaire> ql= new ArrayList<Questionnaire>();
    	if(qd.selectquestiontype()!=null){
        if(u.getRid()==0){
        	ql.add(qd.selectquestiontype().get(0));
        }
        if(u.getRid()==1){
        	ql.add(qd.selectquestiontype().get(1));
        	ql.add(qd.selectquestiontype().get(2));
        }
        if(u.getRid()==2){
        	
        	ql.add(qd.selectquestiontype().get(3));
        }}
       
    	return ql;
    }

    
}

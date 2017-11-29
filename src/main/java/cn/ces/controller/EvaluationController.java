package cn.ces.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ces.entity.Questionnaire;
import cn.ces.service.EvaluationService;
import cn.ces.service.QuestionService;
@Controller
public class EvaluationController {
    private final QuestionService qs;
    private final EvaluationService es;
    

    @Autowired
    public EvaluationController( QuestionService qs,EvaluationService es) {
        this.qs = qs;
        this.es = es;
        
    }
    //查询问卷
  @GetMapping(value = "/selectqOption")
  @ResponseBody
  public List<Questionnaire> selectquestionoption(Integer uid){

      return  qs.selectquestion(uid);
  }
  //模糊查询教师
@GetMapping(value = "/selectpteacher")
@ResponseBody
public Map<String,Object> selectteacher(Integer uid,Integer qid,int offset, int limit){
	


    return  es.selectteacher(uid,qid,offset,limit);
}
}

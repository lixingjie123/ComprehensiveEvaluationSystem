package cn.ces.controller;

import cn.ces.entity.Score;
import cn.ces.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//成绩
@Controller
public class ScoreController {

	private final ScoreService ss;
	
	@Autowired
	public ScoreController( ScoreService ss) {
		this.ss = ss;
	}
	//保存成绩
	@GetMapping(value = "/seavescore",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String seavescore(Score score){
		String msg="保存失败";
		if(ss.seavescore(score)){
			msg="保存成功";
		}
		return  msg;
	}
	
}

package cn.ces.controller;

import cn.ces.entity.Score;
import cn.ces.entity.ScoreDto;
import cn.ces.entity.TeacherScore;
import cn.ces.entity.Users;
import cn.ces.service.ScoreService;
import cn.ces.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//成绩
@Controller
public class ScoreController {

	private final ScoreService ss;
	private final UsersService usersService;
	
	@Autowired
	public ScoreController(ScoreService ss, UsersService usersService) {
		this.ss = ss;
		this.usersService = usersService;
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

	//完成查询所有老师评教成绩
	@GetMapping(value = "/findAllTeacherScore")
	@ResponseBody
	public List<TeacherScore> findAllTeacherScore(){
    	return ss.findAllTeacherScore();
	}

	//完成查看某个老师的成绩详情
	@GetMapping(value = "/seeDetaill")
	@ResponseBody
	public Map<String,Object> seeDetaill(Integer tid){
		List<String> qnameList = new ArrayList<String>();
		double[] scoreArray = new double[5];
		double sum = 0;
		Map<String,Object> map = new HashMap<String, Object>();
		Users users = usersService.selectUserByUid(tid);


		List<ScoreDto> scoreList = ss.selectDetails(tid);
		int i =0;
		while (i<scoreList.size()) {
			scoreArray[i] = scoreList.get(i).getAvg();
			sum += scoreArray[i];
			String qname = scoreList.get(i).getQname();
			qnameList.add(qname);
			i++;
		}
		qnameList.add("总分");
		scoreArray[i] = sum;
		map.put("tname",users.getUname());
		map.put("qnames",qnameList);
		map.put("scores",scoreArray);
		return map;
	}
	
}

package cn.ces.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.ScoreDao;
import cn.ces.dao.TeachersDao;
import cn.ces.entity.Score;

@Service
public class ScoreService {


	private final ScoreDao sd;

	@Autowired
	public ScoreService(ScoreDao sd) {
		this.sd =  sd;

	}
	
	public Boolean seavescore(Score score) {
	    boolean b=false;
		if(sd.insertCourse(score)>0)

		b=true;
		   
		return b;
		
	}

}

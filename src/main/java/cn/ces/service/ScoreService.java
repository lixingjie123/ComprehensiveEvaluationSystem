package cn.ces.service;

import cn.ces.dao.QuestionDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.ScoreDao;
import cn.ces.dao.TeachersDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

	private java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");

	private final ScoreDao sd;
	private final UsersDao usersDao;
	private final QuestionDao questionDao;

	@Autowired
	public ScoreService(ScoreDao sd, UsersDao usersDao, QuestionDao questionDao) {
		this.sd =  sd;

		this.usersDao = usersDao;
		this.questionDao = questionDao;
	}
	
	public Boolean seavescore(Score score) {
	    boolean b=false;
		if(sd.insertCourse(score)>0)
		{
			b = true;
		}
		return b;

	}

	//计算某个老师总分
	private double sumScore(Integer tid){
		List<ScoreDto> scoreDtoList = sd.findScoreByTid(tid);
		double sum = 0;
		for (ScoreDto sdto: scoreDtoList) {
			sum += sdto.getAvg();
		}
		sum = Math.round(sum*10000)/10000.0;
		return sum*100;
	}

	//查询某个老师的得分详情
	public List<ScoreDto> selectDetails(Integer tid){
		List<ScoreDto> scoreList = sd.findScoreByTid(tid);
		if (scoreList!=null&&scoreList.size()!=0){
			for (int i = scoreList.size() - 1; i >= 0; i--){
				Integer qid = scoreList.get(i).getQid();
				double avg = scoreList.get(i).getAvg();
				avg = Math.round(avg*100)/100.0;
				scoreList.get(i).setQname(questionDao.findQnameByQid(qid));
				scoreList.get(i).setAvg(avg*100);
			}
		}
		return scoreList;
	}

	//计算所有已评分的老师的总分
	public List<TeacherScore> findAllTeacherScore(){
		List<Integer> teacherIdList = sd.findTeachers();
		List<TeacherScore> teacherScores = new ArrayList<TeacherScore>();
		if (teacherIdList.size()!=0&&teacherIdList!=null) {
			for (Integer tid: teacherIdList) {
				Users t = usersDao.selectUserByUid(tid);
				if (t!=null){
					TeacherScore ts = new TeacherScore();
					ts.setTid(tid);
					ts.setTname(t.getUname());
					ts.setScore(sumScore(tid));
					teacherScores.add(ts);
				}
			}
		}
		return teacherScores;
	}

}

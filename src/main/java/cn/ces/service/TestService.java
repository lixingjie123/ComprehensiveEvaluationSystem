package cn.ces.service;

import cn.ces.dao.CoursewareMapper;
import cn.ces.dao.TestDao;
import cn.ces.entity.Courseware;
import cn.ces.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {
    private final CoursewareMapper coursewareMapper;
    private final TestDao testDao;

    @Autowired
    public TestService(CoursewareMapper coursewareMapper, TestDao testDao) {
        this.coursewareMapper = coursewareMapper;
        this.testDao = testDao;
    }

    public Courseware selectByPrimaryKey(Integer cwid){
        return coursewareMapper.selectByPrimaryKey(cwid);
    }

    public Courseware selectByCid(Integer cwid){
        return coursewareMapper.selectByPrimaryKey(cwid);
    }

    public Test selectAll(){
        return testDao.selectAll();
    }
}

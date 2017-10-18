package cn.ces.service;

import cn.ces.dao.CoursewareMapper;
import cn.ces.entity.Courseware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {
    private final CoursewareMapper coursewareMapper;

    @Autowired
    public TestService(CoursewareMapper coursewareMapper) {
        this.coursewareMapper = coursewareMapper;
    }

    public Courseware selectByPrimaryKey(Integer cwid){
        return coursewareMapper.selectByPrimaryKey(cwid);
    }
}

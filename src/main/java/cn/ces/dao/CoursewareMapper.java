package cn.ces.dao;

import cn.ces.entity.Courseware;

public interface CoursewareMapper {
    int deleteByPrimaryKey(Integer cwid);

    int insert(Courseware record);

    int insertSelective(Courseware record);

    Courseware selectByPrimaryKey(Integer cwid);

    int updateByPrimaryKeySelective(Courseware record);

    int updateByPrimaryKey(Courseware record);
}
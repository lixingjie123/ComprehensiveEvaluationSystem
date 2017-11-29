package cn.ces.dao;

import cn.ces.entity.Course;
import cn.ces.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface ScoreDao {

  
    

    @Insert("INSERT INTO score (qid,tid,uid,score) " +
            " VALUES (#{qid},#{tid},#{uid},#{score})")
    int insertCourse(Score score);
    
    @Select("select * from score where uid=#{uid} and qid=#{qid} and tid=#{tid}")
    Score selectCoursebyid(@Param("uid") Integer uid,@Param("qid") Integer qid,@Param("tid") Integer tid);

}

package cn.ces.dao;

import cn.ces.entity.Course;
import cn.ces.entity.Power;
import cn.ces.entity.Role;
import cn.ces.entity.Rolepower;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-11-06
 * Time: 15:31
 */
public interface CourseDao {

    @Select("select * from course")
    List<Course> selectCourseAll();
    @Select("select * from course where cname like #{cname} limit #{start}, #{pagesize}")
    List<Course> selectcourse(@Param("start")int start,@Param("pagesize")int pagesize,@Param("cname")String canme);
    
    @Select("select count(*) from course where cname like #{cname}")
    int selectcount(@Param("cname")String cname );
    
    @Select("select * from course where cid = #{cid}")
    Power selectcoursebyid(@Param("cid")int cid);
    
    @Select("select * from course where cname = #{cname}")
    Role selectcoursebyname(@Param("cname")String cname);
    
    
    
    @Insert("INSERT INTO course (cname)" +
           " VALUES (#{cname})")
    int instercourse(Course course);
    
    @Delete("delete from course where cid = #{cid}")
    int delectcoursebyid(@Param("cid")int cid);
    
}

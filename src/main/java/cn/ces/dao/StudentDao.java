package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.ces.entity.Students;
import cn.ces.entity.Teachers;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-11-02
 * Time: 9:51
 */
public interface StudentDao {

    @Insert("INSERT INTO students (sid,clid) " +
            " VALUES (#{sid},#{clid})")
    int insertStudent(@Param("sid")Integer sid, @Param("clid")Integer clid);

    @Delete("delete from students where sid = #{sid}")
    int deleteStudent(Integer sid);
    
    @Select("select * from students where sid = #{sid}")
    Students selectStudent(@Param("sid")Integer sid);
}

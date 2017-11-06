package cn.ces.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

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
}

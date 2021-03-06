package cn.ces.dao;

import cn.ces.entity.Leaders;
import cn.ces.entity.Teachers;
import cn.ces.entity.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 12:08
 */
public interface TeachersDao {

    @Select("select * from teachers where dept_id=#{deptId}")
    List<Teachers> selectTeachersByDept(@Param("deptId") Integer dept_id);
    
    @Select("select * from teachers left join users on users.uid = teachers.tid where dept_id=#{deptId}")
    List<Teachers> selectTeachersByDeptid(@Param("deptId") Integer dept_id);
    
    @Select("select * from teachers ")
    List<Teachers> selectTeachers();
    
    @Select("select count(*) from teachers ")
    int selectCount();
    
    @Select("select * from teachers where tid=#{tid}")
    Teachers selectTeachersbyid(@Param("tid")Integer tid);

    @Insert("INSERT INTO teachers (tid,dept_id) " +
            " VALUES (#{tid},#{deptId})")
    int insertTeachers(@Param("tid")Integer tid,@Param("deptId")Integer dept_id);

    @Delete("delete from teachers where tid = #{tid}")
    int deleteTeacher(Integer tid);
}

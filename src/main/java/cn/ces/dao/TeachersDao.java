package cn.ces.dao;

import cn.ces.entity.Leaders;
import cn.ces.entity.Teachers;
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
}

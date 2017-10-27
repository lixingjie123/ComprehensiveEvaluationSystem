package cn.ces.dao;

import cn.ces.entity.Leaders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 12:02
 */
public interface LeadersDao {

    @Select("select * from leaders where dept_id=#{deptId}")
    List<Leaders> selectLeadersByDept(@Param("deptId") Integer dept_id);
}
package cn.ces.dao;

import cn.ces.entity.Leaders;
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
 * Time: 12:02
 */
public interface LeadersDao {

    @Select("select * from leaders where dept_id=#{deptId}")
    List<Leaders> selectLeadersByDept(@Param("deptId") Integer dept_id);
    
    @Select("select * from leaders where lid=#{lid}")
    Leaders selectLeadersByid(@Param("lid") Integer lid);

    @Insert("INSERT INTO leaders (lid,dept_id) " +
            " VALUES (#{lid},#{deptId})")
    int insertLeader(@Param("lid")Integer lid, @Param("deptId")Integer dept_id);

    @Delete("delete from leaders where lid = #{lid}")
    int deleteLeader(Integer lid);
}

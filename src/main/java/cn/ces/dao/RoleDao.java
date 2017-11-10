package cn.ces.dao;

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
public interface RoleDao {

    @Select("select * from role")
    List<Role> selectRoleAll();
    @Select("select * from role where rname like #{rname} limit #{start}, #{pagesize}")
    List<Role> selectrole(@Param("start")int start,@Param("pagesize")int pagesize,@Param("rname")String ranme);
    
    @Select("select count(*) from role where rname like #{rname}")
    int selectcount(@Param("rname")String rname );
    
    @Select("select * from power where pid = #{pid}")
    Power selectpowerbyid(@Param("pid")int pid);
    
    @Select("select * from role where rname = #{rname}")
    Role selectrolebyname(@Param("rname")String rname);
    
    
    
    @Insert("INSERT INTO role (rname)" +
           " VALUES (#{rname})")
    int insterrole(Role role);
    
    @Delete("delete from role where rid = #{rid}")
    int delectrolebyid(@Param("rid")int rid);
    
	@Insert("INSERT INTO rolepower (rid,pid) VALUES (1,1)")
    int insterpoaerrole();
}

package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.ces.entity.Rolepower;

public interface RolePowerDao {
	@Insert("INSERT INTO rolepower (rid,pid) VALUES (#{rid},#{pid})")
	int insterpoaerrole(@Param("rid")Integer rid,@Param("pid")Integer pid);

	@Select("select * from rolepower where rid=#{rid}")
    List<Rolepower> selectpoweroption(@Param("rid")Integer rid);

	@Select("select * from rolepower where pid=#{pid}")
    List<Rolepower> selectPowerByRid(@Param("pid")Integer pid);

    @Delete("delete from rolepower where rid = #{rid}")
    int delectpowerbyid(@Param("rid")int rid);

    @Delete("delete from rolepower where pid = #{pid}")
    int delRPbyPid(@Param("pid")int pid);
}

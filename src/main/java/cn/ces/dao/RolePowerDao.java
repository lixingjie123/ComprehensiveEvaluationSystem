package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import cn.ces.entity.Rolepower;

public interface RolePowerDao {
	@Insert("INSERT INTO rolepower (rid,pid) VALUES (#{rid},#{pid})")
	int insterpoaerrole(@Param("rid")Integer rid,@Param("pid")Integer pid);

	@Select("select * from rolepower where rid=#{rid}")
    List<Rolepower> selectpoweroption(@Param("rid")Integer rid);

	@Select("select * from rolepower where pid=#{pid}")
    List<Rolepower> selectRidByPid(@Param("pid")Integer pid);

	@Select("select * from rolepower where rid=#{rid}")
    int searchRid(int rid);

    @Delete("delete from rolepower where rid = #{rid}")
    int delectpowerbyid(@Param("rid")int rid);

    @Delete("delete from rolepower where pid = #{pid}")
    int delRPbyPid(@Param("pid")int pid);

    @Delete("delete from rolepower where pid = #{pid} and rid=#{rid}")
    int delRP(@Param("rid")int rid, @Param("pid")int pid);
}

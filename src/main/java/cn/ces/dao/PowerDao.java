package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.ces.entity.Power;

public interface PowerDao {
	//模糊查询菜单
     @Select("select * from power where pname like #{pname}")
     List<Power> selectpower (@Param("pname")String panme);
     
     @Select("select * from power")
     List<Power> selectpowertree ();
     //查询所有菜单数量
     @Select("select count(*) from power where pname like #{pname}")
     int selectcount(@Param("pname")String pname );
     //查询菜单通过id
     @Select("select * from power where pid = #{pid}")
     Power selectpowerbyid(@Param("pid")int pid);
     //查询菜单通过pname
     @Select("select * from power where pname = #{pname}")
     Power selectpowerbyname(@Param("pname")String pname);
     

     //修改菜单
     @Update("update  power set pname=#{pname} , url=#{url},fp_id=#{fp_id} where pid = #{pid}")
     int updatapower(Power power);
     
     //插入菜单
     @Insert("INSERT INTO power (pname,url,fp_id)" +
            " VALUES (#{pname},#{url},#{fp_id})")
     int insterpower(Power power);
     //删除菜单
     @Delete("delete from power where pid = #{pid}")
     int delectpowerbyid(@Param("pid")int pid);
}

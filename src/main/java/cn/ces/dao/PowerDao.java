package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.ces.entity.Power;

public interface PowerDao {
     @Select("select * from power where pname like #{pname} limit #{start}, #{pagesize}")
     List<Power> selectpower(@Param("start")int start,@Param("pagesize")int pagesize,@Param("pname")String panme);
     
     @Select("select count(*) from power where pname like #{pname}")
     int selectcount(@Param("pname")String pname );
     
     @Select("select * from power where pid = #{pid}")
     Power selectpowerbyid(@Param("pid")int pid);
     
     @Select("select * from power")
     List<Power> selectpoweroption();
     
     @Insert("INSERT INTO power (pname,url,fp_id)" +
            " VALUES (#{pname},#{url},#{fp_id})")
     int insterpower(Power power);
     
     @Delete("delete from power where pid = #{pid}")
     int delectpowerbyid(@Param("pid")int pid);
}

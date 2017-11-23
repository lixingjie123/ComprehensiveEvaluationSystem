package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.ces.entity.Indexs;
import cn.ces.entity.Power;

public interface IndexDao {
	//模糊查询指标
     @Select("select * from indexs where index_name like #{index_name}")
     List<Indexs> selectindex (@Param("index_name")String index_name);
     
     @Select("select * from power")
     List<Indexs> selectindextree ();
     //查询所有菜单数量
     @Select("select count(*) from power where pname like #{pname}")
     int selectcount(@Param("pname")String pname );
     //查询指标通过id
     @Select("select * from indexs where index_id = #{index_id}")
     Indexs selectindexbyid(@Param("index_id")int index_id);
     //查询菜单通过pname
     @Select("select * from power where pname = #{pname}")
     Power selectpowerbyname(@Param("pname")String pname);
     
     //插入指标
     @Insert("INSERT INTO indexs (index_name,parent_id,weight)" +
            " VALUES (#{index_name},#{parent_id},#{weight})")
     int insterindex(Indexs index);
     //删除指标
     @Delete("delete from indexs where index_id = #{index_id}")
     int delectindexbyid(@Param("index_id")int index_id);
     
     @Update("update  indexs set index_name=#{index_name} , weight=#{weight},parent_id=#{parent_id} where index_id = #{index_id}")
	 int updateindex(Indexs index);
}

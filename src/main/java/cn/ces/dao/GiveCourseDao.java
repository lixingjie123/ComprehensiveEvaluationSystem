package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.ces.entity.GiveCourse;
import cn.ces.entity.Information;
import cn.ces.entity.Rolepower;

public interface GiveCourseDao {
	@Insert("INSERT INTO information (cid,tid,clid) VALUES (#{cid},#{tid},#{clid})")
	    int insterinformation(Information information);
	
	
	@Select("select * from information i left join course c on c.cid=i.cid left join teachers t on t.tid=i.tid left join class cl on cl.clid=i.clid left join users u on u. uid=t.tid where u.uname like #{uname} and  c.cname like #{cname} and  cl.clname like #{clname} limit #{start}, #{pagesize}")
    List<GiveCourse> selectgivecourse(@Param("cname")String cname,@Param("uname")String uname,@Param("clname")String clname,@Param("start")int start,@Param("pagesize")int pagesize);
	
	@Select("select * from information i left join course c on c.cid=i.cid left join teachers t on t.tid=i.tid left join class cl on cl.clid=i.clid left join users u on u. uid=t.tid where  i.clid =#{clid} limit #{start}, #{pagesize}")
    List<GiveCourse> selectgivecoursebycid(@Param("clid") Integer clid,@Param("start")int start,@Param("pagesize")int pagesize);
	
	@Select("select count(*) from information i left join course c on c.cid=i.cid left join teachers t on t.tid=i.tid left join class cl on cl.clid=i.clid left join users u on u. uid=t.tid")
	int selectcount();
	
    @Delete("delete from information where info_id = #{info_id}")
    int delectgivecoursebyid(@Param("info_id")int info_id);
}

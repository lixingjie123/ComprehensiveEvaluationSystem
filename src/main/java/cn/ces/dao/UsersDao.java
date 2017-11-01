package cn.ces.dao;

import cn.ces.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 14:38
 */
public interface UsersDao {

    @Select("select * from users where rid = #{rid}")
    List<Users> selectUsersByrid(@Param("rid")Integer rid);

    @Select("select * from users")
    List<Users> selectAll();

    @Select("select count(*) from users")
    int selectCount();

    @Select("select * from users limit #{pageIndex},#{pageSize}")
    List<Users> selectPageList(@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);
}

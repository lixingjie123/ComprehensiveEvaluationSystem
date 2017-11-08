package cn.ces.dao;

import cn.ces.entity.Users;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from users where rid like #{like_r} and uname like #{like_un} limit #{pageIndex},#{pageSize}")
    List<Users> selectUserByRidAndUname(@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize,
                                        @Param("like_r")String r,@Param("like_un")String un);

    @Select("select count(*) from users  where rid like #{like_r} and uname like #{like_un}")
    int getRidAndUnameCount(@Param("like_r")String r,@Param("like_un")String un);

    @Insert("INSERT INTO users (uid,uname,pwd,sex,phone,rid) " +
            " VALUES (#{uid},#{uname},#{pwd},#{sex},#{phone},#{rid})")
    int insertUser(Users users);

    @Delete("delete from users where uid = #{uid}")
    int deleteUser(Integer uid);

    @Select("select * from users where uid = #{uid}")
    Users selectUserByUid(Integer uid);

    @Update("UPDATE users SET uname = #{uname},pwd = #{pwd}," +
            "sex = #{sex},phone = #{phone} , rid = #{rid} " +
            "WHERE uid = #{uid}")
    int updateUserByUid(Users users);
}

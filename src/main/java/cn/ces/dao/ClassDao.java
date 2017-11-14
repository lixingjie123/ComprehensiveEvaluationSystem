package cn.ces.dao;

import cn.ces.entity.Class;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-11-06
 * Time: 14:07
 */
public interface ClassDao {

    @Select("select * from class")
    List<Class> selectClassAll();

    @Select("select * from class where clname like #{like_cn} limit #{pageIndex},#{pageSize}")
    List<Class> selectPageByClname(@Param("like_cn")String like_cn,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);

    @Select("select count(*) from class where clname like #{like_cn}")
    int getCountByClname(@Param("like_cn")String like_cn);

    @Select("select * from class where clid = #{clid}")
    Class selectClassByClid(Integer clid);

    @Update("update class set fettle = #{fettle} where clid = #{clid}")
    int updateClassOfFettle(@Param("fettle")Integer fettle,@Param("clid") Integer clid);

    @Update("update class set clname = #{clname}, fettle = #{fettle} where clid = #{clid}")
    int updateClass(Class aClass);

    @Insert("INSERT INTO class (clname,fettle) " +
            " VALUES (#{clname},#{fettle})")
    int insertClass(Class aClass);
}

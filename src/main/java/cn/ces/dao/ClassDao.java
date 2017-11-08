package cn.ces.dao;

import cn.ces.entity.Class;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}

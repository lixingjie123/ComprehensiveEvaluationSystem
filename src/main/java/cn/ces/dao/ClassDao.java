package cn.ces.dao;

import cn.ces.entity.Class;
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
}

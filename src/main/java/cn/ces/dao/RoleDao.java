package cn.ces.dao;

import cn.ces.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-11-06
 * Time: 15:31
 */
public interface RoleDao {

    @Select("select * from role")
    List<Role> selectRoleAll();
}

package cn.ces.dao;

import cn.ces.entity.Test;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-23
 * Time: 21:55
 */
public interface TestDao {
    @Select({"select * from test"})
    Test selectAll();
}

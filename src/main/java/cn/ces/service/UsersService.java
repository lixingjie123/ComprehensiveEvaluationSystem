package cn.ces.service;

import cn.ces.dao.UsersDao;
import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-31
 * Time: 10:13
 */

@Service
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    public Map<String,Object> getPageUsers(int pageIndex, int pageSiz){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=usersDao.selectCount();
        List<Users> rows=usersDao.selectPageList(pageIndex,pageSiz);
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }

    public List<Users> selectAllUsers(){
        return usersDao.selectAll();
    }
}

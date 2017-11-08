package cn.ces.service;

import cn.ces.dao.ClassDao;
import cn.ces.entity.Class;
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
 * Date: 2017-11-08
 * Time: 10:56
 */
@Service
public class ClassService {

    private final ClassDao classDao;

    @Autowired
    public ClassService(ClassDao classDao) {
        this.classDao = classDao;
    }

    public Map<String,Object> selectPageByClname(String like_cn, int pageIndex, int pageSiz){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=classDao.getCountByClname(like_cn);
        List<Class> rows=classDao.selectPageByClname(like_cn,pageIndex,pageSiz);
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
}

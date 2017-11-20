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

    //按条件查询
    public Map<String,Object> selectPageByClname(String like_cn, int pageIndex, int pageSiz){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=classDao.getCountByClname(like_cn);
        List<Class> rows=classDao.selectPageByClname(like_cn,pageIndex,pageSiz);
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }

    //修改班级状态
    public String updateClssOfFettle(Integer clid){
        String msg = "启用成功";
        Integer fettle = 1;
        try{
            Class  aClass = classDao.selectClassByClid(clid);
            fettle = aClass.getFettle();
            if (fettle!=0){
                fettle = 0;
                msg = "禁用成功";
            }else {
                fettle = 1;
                msg = "启用成功";
            }
            classDao.updateClassOfFettle(fettle,clid);
        }catch (Exception e){
            msg=e.getMessage();
        }
        return msg;
    }

    //通过clid查询Class
    public Class selectClassByClid(Integer clid){
        return classDao.selectClassByClid(clid);
    }
    
    public List<Class> selectClass(){
        return classDao.selectClassAll();
    }

    //修改班级
    public String UpdateClass(Class aClass){
        String msg =  "修改失败!";
        try{ int p = classDao.updateClass(aClass);
            if (p!=0){
                msg = "修改成功！";
            }
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }

    //新增班级
    public String AddClass(Class aClass){
        String msg = "添加失败";
        try{ int p = classDao.insertClass(aClass);
            if (p!=0){
                msg = "添加成功！";
            }
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }
}

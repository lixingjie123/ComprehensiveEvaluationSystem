package cn.ces.service;

import cn.ces.dao.LeadersDao;
import cn.ces.dao.StudentDao;
import cn.ces.dao.TeachersDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final UsersDao usersDao;
    private final TeachersDao teachersDao;
    private final StudentDao studentDao;
    private final LeadersDao leadersDao;

    @Autowired
    public UsersService(TeachersDao teachersDao, UsersDao usersDao, StudentDao studentDao, LeadersDao leadersDao) {
        this.teachersDao = teachersDao;
        this.usersDao = usersDao;
        this.studentDao = studentDao;
        this.leadersDao = leadersDao;
    }

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


    //批量添加用户   并把未添加成功的用户返回
    public List<Users> insertUsers(List<Users> usersList){
        List<Users> usersList1 = new ArrayList<Users>();
        for (int i = 0;usersList.size()>i;i++){
            int t = 0;
            Users users = usersList.get(i);
            t = usersDao.insertUser(users);
            if (t==0){
                usersList1.add(users);
            }else {
                switch (users.getRid()) {
                    case 0: teachersDao.insertTeachers(users.getUid(),users.getOther_id());break;
                    case 1: studentDao.insertStudent(users.getUid(),users.getOther_id());break;
                    case 2: leadersDao.insertLeader(users.getUid(),users.getOther_id());break;
                }
            }
        }
        return usersList1;
    }
}

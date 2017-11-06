package cn.ces.service;

import cn.ces.dao.*;
import cn.ces.entity.Department;
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
    private final DepartmentDao departmentDao;

    @Autowired
    public UsersService(TeachersDao teachersDao, UsersDao usersDao, StudentDao studentDao, LeadersDao leadersDao, DepartmentDao departmentDao) {
        this.teachersDao = teachersDao;
        this.usersDao = usersDao;
        this.studentDao = studentDao;
        this.leadersDao = leadersDao;
        this.departmentDao = departmentDao;
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
    public String insertUsers(List<Users> usersList){
        String msg ="添加成功";
        List<Users> usersList1 = new ArrayList<Users>();

        try {
            for (int i = 0;usersList.size()>i;i++){
            int t = 0;
            Users users = usersList.get(i);
            t = usersDao.insertUser(users);
            if (t==0){
                usersList1.add(users);
            }else {
                switch (users.getRid()) {
                    case 1: teachersDao.insertTeachers(users.getUid(),users.getOther_id());break;
                    case 0: studentDao.insertStudent(users.getUid(),users.getOther_id());break;
                    case 2: leadersDao.insertLeader(users.getUid(),users.getOther_id());break;
                }
            }
        }
        }catch (Exception e){
            msg = e.getMessage();
        }

        return msg;
    }

    public String deleteUser(Integer uid){
        String msg = "删除成功";
        Users user = usersDao.selectUserByUid(uid);
        try {
            switch (user.getRid()) {
                case 1: teachersDao.deleteTeacher(uid);break;
                case 0: studentDao.deleteStudent(uid);break;
                case 2: leadersDao.deleteLeader(uid);break;
            }
            usersDao.deleteUser(uid);
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }

    //生成部门动态下拉列表框
    public List<Department> selectDeptOption(){
        return departmentDao.selectDeptAll();
    }
}

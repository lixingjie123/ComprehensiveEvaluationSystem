package cn.ces.service;

import cn.ces.dao.*;
import cn.ces.entity.*;
import cn.ces.entity.Class;
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
    private final ClassDao classDao;
    private final RoleDao roleDao;
    private final RolePowerDao rolePowerDao;
    private final PowerDao powerDao;

    @Autowired
    public UsersService(TeachersDao teachersDao, UsersDao usersDao, StudentDao studentDao, LeadersDao leadersDao,
                        DepartmentDao departmentDao, ClassDao classDao, RoleDao roleDao, RolePowerDao rolePowerDao,
                        PowerDao powerDao) {
        this.teachersDao = teachersDao;
        this.usersDao = usersDao;
        this.studentDao = studentDao;
        this.leadersDao = leadersDao;
        this.departmentDao = departmentDao;
        this.classDao = classDao;
        this.roleDao = roleDao;
        this.rolePowerDao = rolePowerDao;
        this.powerDao = powerDao;
    }


    //分页查询
    public Map<String,Object> getPageUsers(int pageIndex, int pageSiz){
        Map<String,Object> result = new HashMap<String,Object>();
        int total=usersDao.selectCount();
        List<Users> rows=usersDao.selectPageList(pageIndex,pageSiz);
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }

    //按条件分页查询
    public Map<String,Object> selectUserByRidAndUname(int pageIndex, int pageSiz,String like_r,String like_un){
        Map<String,Object> result = new HashMap<String,Object>();
        //获取查询结果总数
        int total=usersDao.getRidAndUnameCount(like_r,like_un);
        //获取查询结果
        List<Users> rows=usersDao.selectUserByRidAndUname(pageIndex,pageSiz,like_r,like_un);
        result.put("total",total);
        result.put("rows",rows);
        return result;
    }
    public List<Users> selectAllUsers(){
        return usersDao.selectAll();
    }

    //查询uid用户
    public Users selectUserByUid(Integer uid){
        return usersDao.selectUserByUid(uid);
    }


    ///批量添加用户   并把未添加成功的用户返回
    public String insertUsers(List<Users> usersList){
        String msg ="添加成功";
        List<Users> usersList1 = new ArrayList<Users>();
        try {
            for (int i = 0; usersList.size() > i; i++) {
                int t = 0;
                Users users = usersList.get(i);
                users.setRid(roleDao.selectRidByName(users.getRname()));
                t = usersDao.insertUser(users);
                if (t == 0) {
                    usersList1.add(users);
                } else {
                    //尚未实现的功能
//                    int deptid;
//                    int clid;
//                    System.out.println(users.getOther_name());
//                    switch (users.getRid()) {
//                        case 1:
//                            deptid = departmentDao.selectDeptIdByDeptName(users.getOther_name());
//                            teachersDao.insertTeachers(users.getUid(), deptid);
//                            break;
//                        case 0:
//                            clid = classDao.selectClidByClname(users.getOther_name());
//                            studentDao.insertStudent(users.getUid(), clid);
//                            break;
//                        case 2:
//                            deptid = departmentDao.selectDeptIdByDeptName(users.getOther_name());
//                            leadersDao.insertLeader(users.getUid(), deptid);
//                            break;
//                        default:
//                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            msg = "数据存在重复或数据格式错误！";
        }

        return msg;
    }

    //删除用户
    public String deleteUser(Integer uid){
        String msg = "删除成功";
        Users user = usersDao.selectUserByUid(uid);
        try {
            switch (user.getRid()) {
                case 1: teachersDao.deleteTeacher(uid);break;
                case 0: studentDao.deleteStudent(uid);break;
                case 2: leadersDao.deleteLeader(uid);break;
                default:
            }
            usersDao.deleteUser(uid);
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }

    //生成部门动态下拉列表框
    public List<Department> selectDeptOption(){
        return departmentDao.selectDepartmentall();
    }

    //生成班级动态下拉列表
    public List<Class> selectClassOption(){
        return classDao.selectClassAll();
    }

    //生成动态角色下拉框
    public List<Role> selectRoleOption(){
        return roleDao.selectRoleAll();
    }

    //通过Uid修改Users
    public String updateUsersByUid(Users users){
        String msg = "修改成功";

        try {
            usersDao.updateUserByUid(users);
        }catch (Exception e){
            msg = e.getMessage();
        }
        return msg;
    }

    //通过用户角色查询该用户的权限菜单
    public String  selectPowerByRid(Integer rid){
        //根据用户权限(rid)获取pid列表
        List<Rolepower> rolepowerList = rolePowerDao.selectpoweroption(rid);
        List<Power> powerList = new ArrayList<Power>();
        //遍历pid列表
        for (Rolepower rolepower: rolepowerList) {
            //通过pid列表获取对应菜单
            Power power = powerDao.selectpowerbyid(rolepower.getPid());
            //若不为空 添加进菜单列表
            if (power!=null){
                powerList.add(power);
            }
        }
        String jsonMenu = "[{" +
                "                        id: '1'," +
                "                        menu: ";
        jsonMenu+="[";
        //遍历菜单列表
        for (Power power: powerList) {
            if (power.getFp_id()==1){
                jsonMenu += "{";
                jsonMenu += "id:'"+power.getPid()+"',";
                jsonMenu += "text:'"+power.getPname()+"',";
                jsonMenu += "items: [";
                for (Power p:powerList) {
                    if (p.getFp_id().equals(power.getPid())){
                        jsonMenu += "{id:'"+p.getPid()+"',";
                        jsonMenu += "text:'"+p.getPname()+"',";
                        jsonMenu += "href:' "+p.getUrl()+"'},";
                    }
                }
                jsonMenu += "]},";
            }
        }
        jsonMenu += "]}];";
        return jsonMenu;
    }

    public String selectRnameByrid(Integer rid){
        return roleDao.selectRnameByRid(rid).toString();
    }
}

package cn.ces.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ces.dao.RoleDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Users;

@Service
public class LoginService {

	private final UsersDao usersDao;
	private final RoleDao roleDao;
	
	@Autowired
	public LoginService(UsersDao usersDao, RoleDao roleDao) {
		this.usersDao = usersDao;
		this.roleDao = roleDao;
	}
	
	public Map<String, Object> loginService(Integer uid,String pwd) {
		String msg = "登录失败";
		int p=0;
		Map<String,Object> result = new HashMap<String,Object>();
			Users users = usersDao.selectUserByUid(uid);
			if(users!=null) {
					if(pwd.equals(users.getPwd())) {
						msg="登录成功";
						p = 1;
					}else {
						msg="账号与密码不匹配";
					}	
			        }else{
				        msg = "该账号不存在";
			}
		result.put("msg", msg);
		result.put("p", p);
		return result;
	}

}

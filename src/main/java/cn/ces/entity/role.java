package cn.ces.entity;

import cn.ces.dao.UsersDao;
import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Table(name="Role")
public class Role extends BaseModel{
	@Column(name = "rid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	rid;

    @Column(name = "rname",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String rname;

    private List<Users> usersList;

    private final UsersDao usersDao;

    @Autowired
    public Role(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public List<Users> getUsersList() {
        usersList = usersDao.selectUsersByrid(rid);
        return usersList;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}

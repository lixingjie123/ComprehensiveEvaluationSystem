package cn.ces.entity;

import cn.ces.dao.LeadersDao;
import cn.ces.dao.TeachersDao;
import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 10:33
 */

@Table(name = "department")
public class Department extends BaseModel{

    @Column(name = "dept_id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer dept_id;

    @Column(name = "dept_name",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String dept_name;

    List<Leaders> leaderList;

    List<Teachers> teacherList;

    public List<Leaders> getLeaderList() {
        return leaderList;
    }

    public void setLeaderList(List<Leaders> leaderList) {
        this.leaderList = leaderList;
    }

    public List<Teachers> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teachers> teacherList) {
        this.teacherList = teacherList;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
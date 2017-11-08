package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 10:40
 */

@Table(name = "class")
public class Class extends BaseModel{

    @Column(name = "clid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer clid;

    @Column(name = "clname",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String clname;

    @Column(name = "status",type = MySqlTypeConstant.INT,length = 1)
    private Integer status;

    private List<Students> studentList;

    public List<Students> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Students> studentList) {
        this.studentList = studentList;
    }

    public Integer getClid() {
        return clid;
    }

    public void setClid(Integer clid) {
        this.clid = clid;
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

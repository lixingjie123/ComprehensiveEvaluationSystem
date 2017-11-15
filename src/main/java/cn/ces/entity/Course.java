package cn.ces.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 10:37
 */

@Table(name = "course")
public class Course extends BaseModel {

    @Column(name = "cid",type = MySqlTypeConstant.INT,length = 11,isAutoIncrement = true,isKey = true)
    private Integer cid;

    @Column(name = "cname",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String cname;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}

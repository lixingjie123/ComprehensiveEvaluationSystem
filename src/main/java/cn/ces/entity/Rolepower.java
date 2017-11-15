package cn.ces.entity;


import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name="rolepower")
public class Rolepower extends BaseModel {

	@Column(name = "rpid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	rpid;

    @Column(name = "rid",type = MySqlTypeConstant.INT,length = 11)
    private Integer	rid;
    
    @Column(name = "pid",type = MySqlTypeConstant.INT,length = 11)
    private Integer	pid;

    public Integer getRpid() {
        return rpid;
    }

    public void setRpid(Integer rpid) {
        this.rpid = rpid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}

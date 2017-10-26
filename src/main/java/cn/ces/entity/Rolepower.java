package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

@Table(name="rolepower")
public class Rolepower extends BaseModel {

	@Column(name = "rpid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	rpid;

    @Column(name = "rid",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	rid;
    
    @Column(name = "pid",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	pid;

    public Integer getRpid() {
        return rpid;
    }

    public void setRpid(Integer rpid) {
        this.rpid = rpid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}

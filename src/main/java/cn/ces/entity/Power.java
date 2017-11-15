package cn.ces.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name="power")
public class Power extends BaseModel{
	@Column(name = "pid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	pid;

    @Column(name = "pname",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	pname;
    
    @Column(name = "url",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	url;
    
    @Column(name = "fp_id",type = MySqlTypeConstant.INT,length = 11)
    private Integer	fp_id;
    
private String fname;

    

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFp_id() {
        return fp_id;
    }

    public void setFp_id(Integer fp_id) {
        this.fp_id = fp_id;
    }
}

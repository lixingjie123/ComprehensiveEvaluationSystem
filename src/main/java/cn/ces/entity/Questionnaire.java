package cn.ces.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 10:49
 */

@Table(name = "questionnaire")
public class Questionnaire extends BaseModel{

    @Column(name = "qid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer qid;

    @Column(name = "qname",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String qname;

    @Column(name = "fettle",type = MySqlTypeConstant.INT,length = 1)
    private Integer fettle;

    private List<Indexs> indexList;

    public List<Indexs> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<Indexs> indexList) {
        this.indexList = indexList;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

	public Integer getFettle() {
		return fettle;
	}

	public void setFettle(Integer fettle) {
		this.fettle = fettle;
	}


}

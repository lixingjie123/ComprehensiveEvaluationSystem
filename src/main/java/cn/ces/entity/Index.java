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
 * Time: 11:00
 */

@Table(name = "index")
public class Index extends BaseModel {

    @Column(name = "index_id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer index_id;

    @Column(name = "index_name",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String index_name;

    @Column(name = "qid",type = MySqlTypeConstant.INT,length = 11)
    private Integer qid;

    @Column(name = "parent_id",type = MySqlTypeConstant.INT,length = 11)
    private Integer parent_id;

    @Column(name = "weight",type = MySqlTypeConstant.DOUBLE,length = 10,decimalLength = 2)
    private Double weight;

    private List<Option> optionList;

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getIndex_id() {
        return index_id;
    }

    public void setIndex_id(Integer index_id) {
        this.index_id = index_id;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
}

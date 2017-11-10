package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 10:53
 */

@Table(name = "option")
public class Option extends BaseModel{

    @Column(name = "oid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer oid;

    @Column(name = "oname",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String oname;

    @Column(name = "index_id",type = MySqlTypeConstant.INT,length = 11)
    private Integer index_id;

    @Column(name = "weight",type = MySqlTypeConstant.DOUBLE,length = 10,decimalLength = 2)
    private Double weight;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public Integer getIndex_id() {
        return index_id;
    }

    public void setIndex_id(Integer index_id) {
        this.index_id = index_id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

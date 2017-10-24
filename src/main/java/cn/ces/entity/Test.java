package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

import java.util.Date;

@Table(name = "Test")
public class Test extends BaseModel {

    private static final long serialVersionUID = 5199200306752426433L;

    @Column(name = "id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	id;

    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,length = 111)
    private String	name;

    @Column(name = "description",type = MySqlTypeConstant.TEXT)
    private String	description;

    @Column(name = "create_time",type = MySqlTypeConstant.DATETIME)
    private Date create_time;

    @Column(name = "update_time",type = MySqlTypeConstant.DATETIME)
    private Date	update_time;

    @Column(name = "lifecycle",type = MySqlTypeConstant.CHAR,length = 1,isNull=false)
    private String	lifecycle;

    // get和set方法这里就不例举了太多


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
/*
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }*/

    public String getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(String lifecycle) {
        this.lifecycle = lifecycle;
    }
/*
    public Double getDekes() {
        return dekes;
    }

    public void setDekes(Double dekes) {
        this.dekes = dekes;
    }*/
}

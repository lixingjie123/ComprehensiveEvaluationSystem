package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;
@Table(name="power")
public class power extends BaseModel{
	@Column(name = "power_id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	power_id;

    @Column(name = "power_name",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	power_name;
    
    @Column(name = "power_url",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	power_url;
    
    @Column(name = "f_powerid",type = MySqlTypeConstant.INT,length = 11)
    private Integer	f_powerid;
}

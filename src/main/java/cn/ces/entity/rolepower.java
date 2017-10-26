package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

@Table(name="rolepower")
public class rolepower extends BaseModel {

	@Column(name = "rolepower_id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	rolepower_id;

    @Column(name = "role_id",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	role_id;
    
    @Column(name = "power_id",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	power_id;
}

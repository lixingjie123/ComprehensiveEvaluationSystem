package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

@Table(name="role")
public class role extends BaseModel{
	@Column(name = "role_id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer	role_id;

    @Column(name = "role_name",type = MySqlTypeConstant.VARCHAR,length = 50)
    private String	role_name;

}

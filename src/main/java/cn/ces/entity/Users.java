package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;




@Table(name="users")
public class Users extends BaseModel {
	


		@Column(name = "user_id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
	    private Integer	user_id;

	    @Column(name = "user_name",type = MySqlTypeConstant.VARCHAR,length = 50)
	    private String	user_name;
	    
	    @Column(name = "password",type = MySqlTypeConstant.VARCHAR,length = 50)
	    private String	password;

	    @Column(name = "sex",type = MySqlTypeConstant.VARCHAR,length = 50)
	    private String	sex; 
	    
	    @Column(name = "phone",type = MySqlTypeConstant.VARCHAR,length = 50)
	    private String	phone; 
	    
	    @Column(name = "role_id",type = MySqlTypeConstant.VARCHAR,length = 50)
	    private String	role_id;

		public Integer getUser_id() {
			return user_id;
		}

		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}


		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getRole_id() {
			return role_id;
		}

		public void setRole_id(String role_id) {
			this.role_id = role_id;
		}

		 

}

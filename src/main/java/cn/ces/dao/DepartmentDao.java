package cn.ces.dao;

import cn.ces.entity.Department;
import cn.ces.entity.Power;
import cn.ces.entity.Users;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 15:18
 */
public interface DepartmentDao {
	
	@Select("select * from department where dept_name like #{dept_name} limit #{pageIndex},#{pageSize}")
    List<Department> selectDepartment(@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize,@Param("dept_name")String dept_name);

	@Select("select count(*) from department where dept_name like #{dept_name}")
    int selectDepartmentcount(@Param("dept_name")String dept_name);
	
    @Select("select * from department where dept_id = #{dept_id}")
    Department selectDepartmentByid(@Param("dept_id")int dept_id);
    
    @Select("select * from department where dept_name = #{dept_name}")
    Department selectDepartmentbyname(@Param("dept_name")String dept_name);

    @Select("select * from department")
    List<Department> selectDepartmentAll();

   
}


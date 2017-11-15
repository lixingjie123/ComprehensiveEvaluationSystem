package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.ces.entity.Department;
import cn.ces.entity.Power;

public interface DepartmentDao {

	@Select("select * from department where dept_name like #{dept_name} limit #{pageIndex},#{pageSize}")
    List<Department> selectDepartment (@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize,
    		                           @Param("dept_name")String dept_name);
    
    @Select("select count(*) from department where dept_name like #{dept_name}")
    int selectDepartmentcount(@Param("dept_name")String dept_name);
    
    @Select("select * from department where dept_id = #{dept_id}")
    Department selectDepartmentbyid(@Param("dept_id")int dept_id);
    
    @Select("select * from department where dept_name = #{dept_name}")
    Power selectDepartmentbyname(@Param("dept_name")String dept_name);
    
    @Select("select * from department")
    List<Department> selectDepartmentall();
}

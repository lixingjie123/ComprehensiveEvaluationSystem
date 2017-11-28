package cn.ces.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.ces.entity.Class;
import cn.ces.entity.Department;
import cn.ces.entity.Power;

public interface DepartmentDao {

	@Select("select * from department where dept_name like #{dept_name} limit #{pageIndex},#{pageSize}")
    List<Department> selectDepartment (@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize,
    		                           @Param("dept_name")String dept_name);
    
    @Select("select count(*) from department where dept_name like #{dept_name}")
    int selectDepartmentcount(@Param("dept_name")String dept_name);
    
    @Select("select * from department where dept_id = #{dept_id}")
    Department selectDepartmentbyid(Integer dept_id);
    
    @Select("select * from department where dept_name = #{dept_name}")
    Department selectDepartmentbyname(@Param("dept_name")String dept_name);
    
    @Select("select * from department")
    List<Department> selectDepartmentall();
    
    @Insert("INSERT INTO department (dept_name,fettle) " + " VALUES (#{dept_name},#{fettle})")
    int insertDepartment(Department aDepartment);
    
    @Update("update department set fettle = #{fettle} where dept_id = #{dept_id}")
    int updateDepartmentOfFettle(@Param("fettle")Integer fettle,@Param("dept_id") Integer dept_id);
    
    @Update("update department set dept_name = #{dept_name}, fettle = #{fettle} where dept_id = #{dept_id}")
    int updateDepartment(Department aDepartment);

    @Select("select dept_id from department where dept_name = #{deptname}")
    Integer selectDeptIdByDeptName(String deptname);
}

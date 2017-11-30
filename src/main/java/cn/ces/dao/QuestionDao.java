package cn.ces.dao;

import cn.ces.entity.Course;
import cn.ces.entity.Power;
import cn.ces.entity.Questionnaire;
import cn.ces.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface QuestionDao {

    @Select("select * from course")
    List<Course> selectCourseAll();
    @Select("select * from Questionnaire where qname like #{qname} limit #{start}, #{pagesize}")
    List<Questionnaire> selectquestion(@Param("start")int start,@Param("pagesize")int pagesize,@Param("qname")String qanme);
    
    @Select("select count(*) from course where cname like #{cname}")
    int selectcount(@Param("cname")String cname );
    
    @Select("select * from Questionnaire where qid = #{qid}")
    Questionnaire selectQuestionnairebyid(@Param("qid")int qid);
    
    @Select("select * from Questionnaire where fettle = 1")
    List<Questionnaire> selectquestiontype();

    //通过Qid查询问卷名称
    @Select("SELECT qname from questionnaire where qid = #{qid}")
    String findQnameByQid(Integer qid);
    
    @Insert("INSERT INTO course (cname)" +
           " VALUES (#{cname})")
    int instercourse(Course course);
    
    @Update("Update questionnaire set qname = #{qname} where qid = #{qid}")
    int upquestion(@Param("qid")Integer qid,@Param("qname")String qanme);
    
    @Update("Update questionnaire set fettle = #{fettle} where qid = #{qid}")
    int upfetter(Questionnaire q);
    
}

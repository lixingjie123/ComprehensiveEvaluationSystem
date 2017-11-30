package cn.ces.dao;

import cn.ces.entity.Course;
import cn.ces.entity.Score;
import cn.ces.entity.ScoreDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ScoreDao {

    @Insert("INSERT INTO score (qid,tid,uid,score) " +
            " VALUES (#{qid},#{tid},#{uid},#{score})")
    int insertCourse(Score score);
    
    @Select("select * from score where uid=#{uid} and qid=#{qid} and tid=#{tid}")
    Score selectCoursebyid(@Param("uid") Integer uid,@Param("qid") Integer qid,@Param("tid") Integer tid);

    //联表查询按角色分类成绩平均分，参数教师编号，问卷编号
    @Select("select qid,rid,tid,avg(score) avg FROM score as s JOIN users AS u on s.uid = u.uid WHERE qid = #{qid} and tid = #{tid} GROUP BY rid")
    List<ScoreDto> avgScoreByTidAndQid(@Param("qid")Integer qid, @Param("tid") Integer tid);

    //接受某问卷成绩前五
    @Select("SELECT qid, tid, avg(score) avg FROM score AS s WHERE qid = #{qid} GROUP BY tid ORDER BY AVG(score) desc LIMIT 5")
    List<ScoreDto> avgScoreByQidTopFive(@Param("qid")Integer qid);

    //接受某问卷成绩后五
    @Select("SELECT qid, tid, avg(score) avg FROM score AS s WHERE qid = #{qid} GROUP BY tid ORDER BY AVG(score) ASC LIMIT 5")
    List<ScoreDto> avgScoreByQidLastFive(@Param("qid")Integer qid);

    //查询某问卷受评教师个数
    @Select("SELECT COUNT(distinct tid) FROM\tscore  WHERE qid = #{qid}")
    int countTidByQid(Integer qid);

    //获取有评教成绩的老师
    @Select("SELECT DISTINCT tid from score ")
    List<Integer> findTeachers();

    //获取某个老师各问卷成绩
    @Select("select qid,tid,avg(score) avg FROM score  WHERE  tid = #{tid} GROUP BY qid")
    List<ScoreDto> findScoreByTid(Integer tid);
}

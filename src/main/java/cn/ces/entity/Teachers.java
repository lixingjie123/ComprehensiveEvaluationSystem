package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Users: lixingjie
 * Date: 2017-10-26
 * Time: 9:27
 */

@Table(name = "teachers")
public class Teachers extends BaseModel{

    @Column(name = "tid",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer tid;

    @Column(name = "dept_id",type = MySqlTypeConstant.INT,length = 11,isNull = true)
    private Integer dept_id;

    private List<Score> scoreList;

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }
}

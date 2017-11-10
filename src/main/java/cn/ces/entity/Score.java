package cn.ces.entity;

import com.mybatis.enhance.store.annotation.Column;
import com.mybatis.enhance.store.annotation.Table;
import com.mybatis.enhance.store.command.BaseModel;
import com.mybatis.enhance.store.constants.MySqlTypeConstant;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-26
 * Time: 11:34
 */

@Table(name = "score")
public class Score extends BaseModel{

    @Column(name = "score_id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer score_id;

    @Column(name = "qid",type = MySqlTypeConstant.INT,length = 11)
    private Integer qid;

    @Column(name = "uid",type = MySqlTypeConstant.INT,length = 11 )
    private Integer uid;

    @Column(name = "tid",type = MySqlTypeConstant.INT,length = 11)
    private Integer tid;

    @Column(name = "score",type = MySqlTypeConstant.DOUBLE,length = 11,decimalLength = 2)
    private Double score;

    public Integer getScore_id() {
        return score_id;
    }

    public void setScore_id(Integer score_id) {
        this.score_id = score_id;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

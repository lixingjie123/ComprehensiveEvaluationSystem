

package cn.ces.service;

import cn.ces.dao.IndexDao;
import cn.ces.dao.PowerDao;
import cn.ces.dao.UsersDao;
import cn.ces.entity.Indexs;
import cn.ces.entity.Power;
import cn.ces.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Service
public class IndexService {
    @Autowired
    private IndexDao id;

    //模糊查询指标
    public List<Indexs> selectindex(String index_name){
    	return id.selectindex(index_name);
    }
    public List<Indexs> selectindextree(){
    	return id.selectindextree();
    }

    //插入指标
    public Boolean insterindex(Indexs index){
    	Boolean b = false;
    	if(id.insterindex(index)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    //删除指标
    public Boolean delectindex(int index_id){
    	Boolean b = false;
    	if(id.delectindexbyid(index_id)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    //修改指标
    public Boolean updateindex(Indexs index){
    	Boolean b = false;
    	if(id.updateindex(index)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
    //修改指标
    public Boolean  updateindexqid(Indexs index){
    	Boolean b = false;
    	if(id. updateindexqid(index)>0){
    		b=true;
    	}else b=false;
    	return b ;
    }
   
    //通过id查询指标
    public Indexs selectindexbyid(int index_id){
    	return id.selectindexbyid(index_id);
    }
  //通过qid查询指标
    public List<Indexs> selectindexbyqid(int qid){
    	return id.selectindexbyqid(qid);
    }

}

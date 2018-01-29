package cn.ces.controller;


import cn.ces.entity.Indexs;
import cn.ces.entity.Power;
import cn.ces.service.IndexService;
import cn.ces.tool.TreeNode;
import cn.ces.tool.TreeNodeTool;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.util.List;


@Controller
public class IndexController {

    private final IndexService is;

    @Autowired
    public IndexController( IndexService is) {
        this.is = is;
    }
  //显示指标列表，模糊查询指标,树形显示
    
    @PostMapping(value = "/indextree",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String indextree(String index_name){
   
    	try {
    		index_name = URLDecoder.decode(index_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	String p="%%";
	System.out.println(index_name);
  if(!"".equals(index_name) &&index_name!=""){
	 p="%"+index_name+"%";} 
    	List<Indexs> Indexlist=is.selectindex(p);
    	TreeNode tl1 = null;
    	String res = null;
    	for(int i=0;i<Indexlist.size();i++){
			tl1=TreeNodeTool.setTreeNode(tl1, Indexlist.get(i).getIndex_id(), Indexlist.get(i).getParent_id(), Indexlist.get(i).getIndex_name(),Indexlist.get(i).getWeight().toString(),false);
		     JSONArray json = JSONArray.fromObject(tl1);
			res= json.get(0).toString();
			res = "[" + res + "]";
		}
    	
    	

        return res ;
    }
    //插入指标
    @PostMapping(value = "/seaveindex",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String seaveindex(Indexs index){
    	String msg; 
       if(is.insterindex(index)){
    	    msg = "添加成功";
       }else {
           msg = "添加失败";
       }

        return msg;
    }
    
    //删除指标
    @GetMapping(value = "/delectindex",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delectindex(int index_id){
    	String msg; 
        if(is.delectindex(index_id)){
     	    msg = "删除成功";
        }else {
            msg = "删除失败";
        }

         return msg;
    	
    	
    }
    
    @GetMapping(value = "/querindexbyid")
    @ResponseBody
    public Indexs querypindexbyid(int index_id){
    	
		
    	Indexs index=is.selectindexbyid(index_id);

    	return index;
    	
    }
    //修改指标
    @PostMapping(value = "/updateindex",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updataindex(Indexs index){
    	String msg; 
    
    	Boolean b = is.updateindex(index);
       if(b){
    	    msg = "添加成功";
       }else {
           msg = "添加失败";
       }

        return msg;
    }
    



    
}

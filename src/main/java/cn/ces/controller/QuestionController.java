package cn.ces.controller;

import cn.ces.entity.Indexs;
import cn.ces.service.IndexService;
import cn.ces.service.QuestionService;
import cn.ces.tool.TreeNode;

import net.sf.json.JSONArray;
import cn.ces.tool.TreeNodeTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


@Controller
public class QuestionController {

    private final QuestionService qs;
    private final IndexService is;
    

    @Autowired
    public QuestionController( QuestionService qs,IndexService is) {
        this.qs = qs;
        this.is = is;
        
    }
      //模糊查询问卷，并分页
    @GetMapping(value = "/selectquestion")
    @ResponseBody
    public Map<String,Object> showquestion(int offset, int limit,String qname){
       	try {
            qname= URLDecoder.decode(qname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(qname);
    	String p="%%";
    	
      if(!qname.equals("null")){
    	 p="%"+qname+"%";} 
        return  qs.selectallquestion(offset, limit,p);
    }
    
  //显示指标列表，模糊查询指标,树形显示
    
    @PostMapping(value = "/indextreequestion",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String indextree(Integer qid){
   

    	List<Indexs> Indexlist=is.selectindextree();
    	TreeNode tl1 = null;
    	String res = null;
    	for(int i=0;i<Indexlist.size();i++){
    		boolean b=false;
    		
    			if(Indexlist.get(i).getQid()==qid){
    				b=true;
    			
    		}
			tl1=TreeNodeTool.setTreeNode(tl1, Indexlist.get(i).getIndex_id(), Indexlist.get(i).getParent_id(), Indexlist.get(i).getIndex_name(),Indexlist.get(i).getWeight().toString(),b);
		     JSONArray json = JSONArray.fromObject(tl1);
			res= json.get(0).toString();
			res = "[" + res + "]";
		}
    	
    	

        return res ;
    }
    //查询显示问卷
    @GetMapping(value = "/showquestion")
    @ResponseBody
    public ModelAndView showquestion(int qid,int tid){
    	ModelAndView modelAndView;
    	List<Indexs> ilist=is.selectindexbyqid(qid);
    	modelAndView=new ModelAndView("forward:questions/show.jsp?tid="+tid);
    	modelAndView.addObject("ilist",ilist);
        return  modelAndView;
    }
    
    //启用/禁用问卷
    @GetMapping(value = "/stopgivecourse",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String stopgivecourse(Integer qid){
    	String msg;
    	
    
       if(qs.stopgivecourse(qid)){
    	    msg = "成功";
       }else msg = "失败";

       
        return msg;
    }

    //修改问卷，并修改指标列表
    @PostMapping(value = "/updataqusetion",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updatarole(Integer qid,String qname,String idlist){
    	String msg = "修改失败";
    	try {
    		qname= URLDecoder.decode(qname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(qname);
    	int ret[] = new int[idlist.length()];
    	StringTokenizer toKenizer = new StringTokenizer(idlist, ",");
    	int i = 0;
    	while (toKenizer.hasMoreElements()) {
         ret[i++] = Integer.valueOf(toKenizer.nextToken());
    		}
    	List<Indexs> ilist=is.selectindexbyqid(qid);
    	if(ilist!=null){
    		for(int k=0;k<ilist.size();k++){
    			Indexs id=ilist.get(k);
    			id.setQid(null);
    			is.updateindexqid(id);
    		}
    	}
    	for(int j=1;j<ret.length;j++){
    		Indexs inde = is.selectindexbyid(ret[j]);
    		if(inde!=null){
    		inde.setQid(qid);
    		
    		is. updateindexqid(inde);
    		}
    	}
    	if(qs.upquestion(qid,qname)){
    		msg="修改成功";
    	}
    	return msg;
    
    }


    
}

package cn.ces.controller;

import cn.ces.entity.Power;
import cn.ces.service.PowerService;
import cn.ces.tool.TreeNode;
import cn.ces.tool.TreeNodeTool;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.util.List;


@Controller
public class PowerController {

    private final PowerService powerService;

    @Autowired
    public PowerController( PowerService powerService) {
        this.powerService = powerService;
    }
  //显示菜单列表，模糊查询菜单,树形显示
    
    @PostMapping(value = "/powertree",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String powertree(String pname){
   
    	try {
			pname = URLDecoder.decode(pname, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	String p="%%";
	System.out.println(pname);
  if(!pname.equals(null)&&pname!=""){
	 p="%"+pname+"%";} 
    	List<Power> powerlist=powerService.selectpower(p);
    	TreeNode tl1 = null;
    	String res = null;
    	for(int i=0;i<powerlist.size();i++){
			tl1=TreeNodeTool.setTreeNode(tl1, powerlist.get(i).getPid(), powerlist.get(i).getFp_id(), powerlist.get(i).getPname(), powerlist.get(i).getUrl(),false);
		     JSONArray json = JSONArray.fromObject(tl1);
			res= json.get(0).toString();
			res = "[" + res + "]";
		}
    	
    	

        return res ;
    }
    //插入菜单
    @PostMapping(value = "/seavepower",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String seavepower(Power power){
    	String msg; 
       if(powerService.insterpower(power)){
    	    msg = "添加成功";
       }else msg = "添加失败";

        return msg;
    }
    
    //删除菜单
    @GetMapping(value = "/delectpower",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delectpower(int pid){
    	String msg; 
        if(powerService.delectpower(pid)){
     	    msg = "删除成功";
        }else msg = "删除失败";

         return msg;
    	
    	
    }
    
    @GetMapping(value = "/querypowerbyid")
    @ResponseBody
    public Power querypowerbyid(int pid){
    	
		
    	Power p=powerService.selectposerbyid(pid);

    	return p;
    	
    }
    //修改菜单
    @PostMapping(value = "/updatapower",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updatapower(Power power){
    	String msg; 
    	Boolean a = powerService.delectpower(power.getPid());
    	power.setPid(null);
    	Boolean b = powerService.insterpower(power);
       if(a&&b){
    	    msg = "添加成功";
       }else msg = "添加失败";

        return msg;
    }
    



    
}

package cn.ces.controller;

import cn.ces.dao.UsersDao;
import cn.ces.entity.Power;
import cn.ces.entity.Users;
import cn.ces.service.PowerService;
import cn.ces.service.UsersService;
import cn.ces.tool.TreeNode;
import cn.ces.tool.TreeNodeTool;

import com.mybatis.enhance.store.manager.common.BaseMysqlCRUDManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class PowerController {

    private final BaseMysqlCRUDManager baseMysqlCRUDManager;
    private final PowerService powerService;

    @Autowired
    public PowerController(BaseMysqlCRUDManager baseMysqlCRUDManager, PowerService powerService) {
        this.baseMysqlCRUDManager = baseMysqlCRUDManager;
        this.powerService = powerService;
    }

    @GetMapping(value = "/selectpower")
    @ResponseBody
    public Map<String,Object>  selectpower(int offset, int limit,String pname){
    
    		try {
				pname=URLDecoder.decode(pname,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	System.out.println(pname);
    	String p="%%";
      if(!pname.equals("null")){
    	 p="%"+pname+"%";} 
        return  powerService.selectallpower(offset, limit,p);
    }
    @GetMapping(value = "/poweroption")
    @ResponseBody
    public List<Power> poweroption(){
    	
    	List<Power> powerlist=powerService.selectpower();

        return powerlist ;
    }
    
    @PostMapping(value = "/powertree",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String powertree(){
    	
    	List<Power> powerlist=powerService.selectpower();
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
    
    @PostMapping(value = "/seavepower",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String seavepower(Power power){
    	String msg; 
       if(powerService.insterpower(power)){
    	    msg = "添加成功";
       }else msg = "添加失败";

        return msg;
    }
    
    @GetMapping(value = "/delectpower",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delectpower(int pid){
    	String msg; 
        if(powerService.delectpower(pid)){
     	    msg = "删除成功";
        }else msg = "删除失败";

         return msg;
    	
    	
    }
    
    @GetMapping(value = "/querypowerbyid",produces = "text/plain;charset=utf-8")
    public Power querypowerbyid(int pid,HttpServletResponse re){
    	
		
			

    	return powerService.selectposerbyid(pid);
    	
    }
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

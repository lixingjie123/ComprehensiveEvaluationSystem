package cn.ces.controller;

import cn.ces.dao.UsersDao;
import cn.ces.entity.Power;
import cn.ces.entity.Role;
import cn.ces.entity.Users;
import cn.ces.service.PowerService;
import cn.ces.service.RoleService;
import cn.ces.service.UsersService;
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
public class RoleController {

    private final BaseMysqlCRUDManager baseMysqlCRUDManager;
    private final RoleService roleService;

    @Autowired
    public RoleController(BaseMysqlCRUDManager baseMysqlCRUDManager, RoleService roleService) {
        this.baseMysqlCRUDManager = baseMysqlCRUDManager;
        this.roleService = roleService;
    }

    @GetMapping(value = "/selectrole")
    @ResponseBody
    public Map<String,Object> selectrole(int offset, int limit,String rname){
       	try {
    		rname=new String(rname.getBytes("iso8859-1"),"UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(rname);
    	String p="%%";
    	
      if(!rname.equals("null")){
    	 p="%"+rname+"%";} 
        return  roleService.selectallrole(offset, limit,p);
    }
    @GetMapping(value = "/roleoption")
    @ResponseBody
    public List<Power> poweroption(){
    	
    	List<Power> powerlist=roleService.selectpower();

        return powerlist ;
    }
    
    @PostMapping(value = "/seaverole",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String seaverole(Role role){
    	String msg; 
       if(roleService.insterrole(role)){
    	    msg = "添加成功";
       }else msg = "添加失败";

        return msg;
    }
    
    @GetMapping(value = "/delectrole",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delectrole(int rid){
    	String msg; 
        if(roleService.delectrole(rid)){
     	    msg = "删除成功";
        }else msg = "删除失败";

         return msg;
    	
    	
    }
    
    @GetMapping(value = "/queryrolebyid",produces = "text/plain;charset=utf-8")
    public Power querypowerbyid(int pid,HttpServletResponse re){
    	
		
			

    	return roleService.selectposerbyid(pid);
    	
    }
    @PostMapping(value = "/updatarole",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updatapower(Role role){
    	String msg; 
    	Boolean a = roleService.delectrole(role.getRid());
    	role.setRid(null);
    	Boolean b = roleService.insterrole(role);
       if(a&&b){
    	    msg = "添加成功";
       }else msg = "添加失败";

        return msg;
    }
    



    
}

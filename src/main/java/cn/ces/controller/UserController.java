package cn.ces.controller;

import cn.ces.entity.Class;
import cn.ces.entity.Power;
import cn.ces.entity.Role;
import cn.ces.entity.Users;
import cn.ces.service.UsersService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lixingjie
 * Date: 2017-10-30
 * Time: 11:34
 */

//用于用户管理
@Controller
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController( UsersService usersService) {
        this.usersService = usersService;
    }

    //用户模糊查询分页显示
    @GetMapping(value = "/selectUserByRidAndUname")
    @ResponseBody
    public Map<String,Object> selectUserByRidAndUname(int offset, int limit, String rid, String uname){
        String un = "%%";
        String r="%%";
        if (!uname.equals(null)&&!"".equals(uname) &&!"null".equals(uname)){
            un = "%"+uname+"%";
        }
        if (!rid.equals(null)&&!"".equals(rid) &&!"null".equals(rid)){
            r = "%"+rid+"%";
        }
        return  usersService.selectUserByRidAndUname(offset,limit,r,un);
    }

    //通过用户编号查询用户
    @GetMapping(value = "/selectUserByUid")
    @ResponseBody
    public Users selectUserByUid(ModelMap map,HttpServletRequest request){
        int uid = Integer.parseInt(request.getParameter("uid"));
        return usersService.selectUserByUid(uid);
    }

    //生成角色下拉框
    @GetMapping(value = "/selectRoleOption")
    @ResponseBody
    public List<Role> selectRoleOption(){
       return usersService.selectRoleOption();
    }

    //生成班级下拉框
    @GetMapping(value = "/selectClassOption")
    @ResponseBody
    public List<Class> selectClassOption(){
        return usersService.selectClassOption();
    }

    //分页查询用户
    @GetMapping(value = "/selectPageUser")
    @ResponseBody
    public Map<String,Object> selectPageUser(int offset, int limit){
        return  usersService.getPageUsers(offset,limit);
    }

    //修改用户信息
    @PostMapping(value = "/updateUserByUid",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updateUserByUid(Users users){
        return usersService.updateUsersByUid(users);
    }

    //通过用户编号删除用户
    @GetMapping(value = "/deleteUser",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String deleteUser(Integer uid){
       return usersService.deleteUser(uid);
    }

    //通过excel的文档批量添加用户
    @PostMapping(value = "/addUsersByExcel",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String addUsersByExcel(MultipartFile file, HttpServletRequest request) throws IOException{

        String msg = "文件上传失败";
        List<Users> usersList = new ArrayList<Users>();

        InputStream inputStream = file.getInputStream();
        // 获取图片扩展名
        String originalFilename = file.getOriginalFilename();
        // 取扩展名，不要"."
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        if ("xls".equals(extName)) {
            // 根据指定的文件输入流导入Excel从而产生Workbook对象
            HSSFWorkbook wkb = new HSSFWorkbook(inputStream);
            // 获取Excel文档中的第一个表单
            HSSFSheet sheet = wkb.getSheetAt(0);
            // 遍历sheet中的每一行
            for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                Users users = new Users();
                //转为Integer类型
                users.setUid((int)(row.getCell(0).getNumericCellValue()));
                users.setUname(row.getCell(1).getStringCellValue());
                users.setPwd(row.getCell(2).getStringCellValue());
                users.setSex(row.getCell(3).getStringCellValue());
                users.setPhone(String.valueOf((int)row.getCell(4).getNumericCellValue()));
                users.setRname(row.getCell(5).getStringCellValue());
//                users.setOther_name(row.getCell(6).getStringCellValue());
                usersList.add(users);
            }
            msg = usersService.insertUsers(usersList);
        }
        return msg;
    }

    //打印用户信息表
    @GetMapping(value = "/onlondUser")
    @ResponseBody
    public void onlondUser(HttpServletResponse response) throws IOException{
        List<Users> usersList = usersService.selectAllUsers();

        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wkb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wkb.createSheet("用户信息表");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        // 设置单元格内容
        cell.setCellValue("用户信息一览表");
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        // 在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
        // 创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("用户编号");
        row2.createCell(1).setCellValue("用户名");
        row2.createCell(2).setCellValue("性别");
        row2.createCell(3).setCellValue("电话");
        row2.createCell(4).setCellValue("角色");
        // 在sheet里创建数据行
        for (int i = 0; i < usersList.size(); i++) {
            HSSFRow row = sheet.createRow(i + 2);
            Users users = usersList.get(i);
            users.setRname(usersService.selectRnameByrid(users.getRid()));
            row.createCell(0).setCellValue(users.getUid());
            row.createCell(1).setCellValue(users.getUname());
            row.createCell(2).setCellValue(users.getSex());
            row.createCell(3).setCellValue(users.getPhone());
            row.createCell(4).setCellValue(users.getRname());
        }

        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        wkb.write(output);
        output.close();
    }

    //下载用户信息模板
    @GetMapping(value = "/onlondTemplate")
    @ResponseBody
    public void onlondTemplate(HttpServletResponse response) throws IOException{
        List<Users> usersList = usersService.selectAllUsers();
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wkb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wkb.createSheet("用户信息表");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        // 设置单元格内容
        cell.setCellValue("用户信息表录入");
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        // 在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
        // 创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("用户编号");
        row2.createCell(1).setCellValue("用户名");
        row2.createCell(2).setCellValue("密码");
        row2.createCell(3).setCellValue("性别");
        row2.createCell(4).setCellValue("电话");
        row2.createCell(5).setCellValue("角色");
        row2.createCell(6).setCellValue("系部/班级");

        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        wkb.write(output);
        output.close();
    }

    /***
     * 通过用户角色查询权限菜单
     * @param rid
     * @return
     */
    @GetMapping(value = "/selectPowerByRid",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String selectPowerByRid(Integer rid){
        return usersService.selectPowerByRid(rid);
    }
}

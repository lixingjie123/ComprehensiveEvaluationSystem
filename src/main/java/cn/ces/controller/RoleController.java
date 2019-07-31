package cn.ces.controller;

import cn.ces.entity.Power;
import cn.ces.entity.Role;
import cn.ces.entity.Rolepower;
import cn.ces.service.PowerService;
import cn.ces.service.RoleService;
import cn.ces.tool.TreeNode;

import net.sf.json.JSONArray;
import cn.ces.tool.TreeNodeTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


@Controller
public class RoleController {

    private final RoleService roleService;
    private final PowerService powerService;

    @Autowired
    public RoleController(RoleService roleService, PowerService powerService) {
        this.roleService = roleService;
        this.powerService = powerService;
    }

    //模糊查询角色，并分页
    @GetMapping(value = "/selectrole")
    @ResponseBody
    public Map<String, Object> selectrole(int offset, int limit, String rname) {
        try {
            rname = URLDecoder.decode(rname, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String p = "%%";
        if (!rname.equals(null) && rname != "") {
            p = "%" + rname + "%";
        }
        return roleService.selectallrole(offset, limit, p);
    }

    //获取菜单下拉框
    @PostMapping(value = "/rolepowertree", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String powertree(int rid) {

        List<Power> powerlist = powerService.selectpowertree();
        List<Rolepower> rolepowerlist = roleService.selectpower(rid);
        TreeNode tl1 = null;
        String res = null;
        for (int i = 0; i < powerlist.size(); i++) {
            Boolean b = false;
            for (int j = 0; j < rolepowerlist.size(); j++) {
                if (powerlist.get(i).getPid().equals(rolepowerlist.get(j).getPid())) {
                    b = true;
                }
            }
            tl1 = TreeNodeTool.setTreeNode(tl1, powerlist.get(i).getPid(), powerlist.get(i).getFp_id(), powerlist.get(i).getPname(), powerlist.get(i).getUrl(), b);
            JSONArray json = JSONArray.fromObject(tl1);
            res = json.get(0).toString();
            res = "[" + res + "]";
        }
        return res;
    }

    //添加角色
    @PostMapping(value = "/seaverole", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String seaverole(Role role, String idlist) {
        String msg;
        int ret[] = new int[idlist.length()];
        StringTokenizer toKenizer = new StringTokenizer(idlist, ",");
        int i = 0;
        while (toKenizer.hasMoreElements()) {
            ret[i++] = Integer.valueOf(toKenizer.nextToken());
        }
        if (roleService.insterrole(role)) {
            msg = "添加角色成功";
        } else {
            msg = "添加失败";
        }
        int rid = roleService.selectrolebyname(role.getRname()).getRid();
        if (roleService.dispower(ret, rid)) {
            msg = "添加成功";
        } else {
            roleService.delectrole(rid);
            msg = "添加失败";
        }
        return msg;
    }

    //删除角色，并删除分配的菜单
    @GetMapping(value = "/delectrole", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delectrole(int rid) {
        String msg;
        if (roleService.delectrole(rid)) {
            roleService.delectpower(rid);
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }
        return msg;
    }

    //修改角色，并修改菜单列表
    @PostMapping(value = "/updatarole", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updatarole(Role role, String idlist) {
        String msg;
        roleService.delectrole(role.getRid());
        roleService.delectpower(role.getRid());
        int ret[] = new int[idlist.length()];
        StringTokenizer toKenizer = new StringTokenizer(idlist, ",");
        int i = 0;
        while (toKenizer.hasMoreElements()) {
            ret[i++] = Integer.valueOf(toKenizer.nextToken());
        }
        if (roleService.insterrole1(role)) {
            msg = "添加角色成功";
        } else {
            msg = "添加失败";
        }

        if (roleService.dispower(ret, role.getRid())) {
            msg = "添加成功";
        } else {
            roleService.delectrole(role.getRid());
            msg = "添加失败";
        }
        return msg;
    }
}

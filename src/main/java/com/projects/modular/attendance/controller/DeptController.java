package com.projects.modular.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Dept;
import com.projects.modular.attendance.model.params.DeptParam;
import com.projects.modular.attendance.service.DeptService;
import com.projects.modular.system.entity.User;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 部门表控制器
 *
 * @author demo
 * @Date 2022-03-01 15:53:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "/modular/dept";

    @Autowired
    private DeptService deptService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/dept.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/dept_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/dept_edit.html";
    }
    @RequestMapping("/getList")  
    @ResponseBody
    public ResponseData getList() {
    	 QueryWrapper<Dept> objectQueryWrapper = new QueryWrapper<>();
    	
    	 List<Dept> list = deptService.getBaseMapper().selectList(objectQueryWrapper);
    	 
        return ResponseData.success(list);
    }
    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DeptParam deptParam) {
        this.deptService.add(deptParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(DeptParam deptParam) {
        this.deptService.update(deptParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(DeptParam deptParam) {
        this.deptService.delete(deptParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(DeptParam deptParam) {
        Dept detail = this.deptService.getById(deptParam.getDeptId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author demo
     * @Date 2022-03-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DeptParam deptParam) {
        return this.deptService.findPageBySpec(deptParam);
    }

}



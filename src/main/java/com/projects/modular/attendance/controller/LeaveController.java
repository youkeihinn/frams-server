package com.projects.modular.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.modular.api.entity.RegisterUser;
import com.projects.modular.api.service.RegisterUserService;
import com.projects.modular.attendance.entity.Leave;
import com.projects.modular.attendance.model.params.AttendanceParam;
import com.projects.modular.attendance.model.params.LeaveParam;
import com.projects.modular.attendance.service.AttendanceService;
import com.projects.modular.attendance.service.LeaveService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;


/**
 * 请假管理控制器
 *
 * @author demo
 * @Date 2022-03-01 15:53:22
 */
@Controller
@RequestMapping("/leave")
public class LeaveController extends BaseController {

    private String PREFIX = "/modular/leave";

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private RegisterUserService registerUserService;
    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/leave.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/leave_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/leave_edit.html";
    }

    
    @RequestMapping("/audit")
    public String audit() {
        return PREFIX + "/audit.html";
    }

    
    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(LeaveParam leaveParam) {
    	leaveParam.setStatus(0);
    	leaveParam.setUserId(ShiroKit.getUser().getId());
        this.leaveService.add(leaveParam);
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
    public ResponseData editItem(LeaveParam leaveParam) {
    	
    	if(ToolUtil.isNotEmpty(leaveParam.getStatus())) {
    		leaveParam.setStatus(leaveParam.getStatus());
    		if(leaveParam.getStatus() == 1) {
    			Leave leave = leaveService.getById(leaveParam.getLeaveId());
    			RegisterUser registerUser = registerUserService.getById(leave.getUserId());
    			AttendanceParam attendanceParam = new AttendanceParam();
    			attendanceParam.setTime(leave.getStartTime());
    			attendanceParam.setType(leave.getType());
    			attendanceParam.setUserId(leave.getUserId());
    			attendanceParam.setNo(registerUser.getNo());
    			attendanceParam.setName(registerUser.getName());
    			attendanceService.add(attendanceParam);
    		}
    	}
    	
    	
        this.leaveService.update(leaveParam);
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
    public ResponseData delete(LeaveParam leaveParam) {
        this.leaveService.delete(leaveParam);
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
    public ResponseData detail(LeaveParam leaveParam) {
        Leave detail = this.leaveService.getById(leaveParam.getLeaveId());
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
    public LayuiPageInfo list(LeaveParam leaveParam) {
        return this.leaveService.findPageBySpec(leaveParam);
    }

}



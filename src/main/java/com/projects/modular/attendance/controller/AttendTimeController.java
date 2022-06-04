package com.projects.modular.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.AttendTime;
import com.projects.modular.attendance.model.params.AttendTimeParam;
import com.projects.modular.attendance.service.AttendTimeService;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;


/**
 * 公司考勤时间控制器
 *
 * @author demo
 * @Date 2022-03-01 15:53:22
 */
@Controller
@RequestMapping("/attendTime")
public class AttendTimeController extends BaseController {

    private String PREFIX = "/modular/attendTime";

    @Autowired
    private AttendTimeService attendTimeService;

    /**
     * 跳转到主页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/attendTime.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/attendTime_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/attendTime_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(AttendTimeParam attendTimeParam) {
        this.attendTimeService.add(attendTimeParam);
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
    public ResponseData editItem(AttendTimeParam attendTimeParam) {
        this.attendTimeService.update(attendTimeParam);
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
    public ResponseData delete(AttendTimeParam attendTimeParam) {
        this.attendTimeService.delete(attendTimeParam);
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
    public ResponseData detail(AttendTimeParam attendTimeParam) {
        AttendTime detail = this.attendTimeService.getById(attendTimeParam.getTime());
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
    public LayuiPageInfo list(AttendTimeParam attendTimeParam) {
        return this.attendTimeService.findPageBySpec(attendTimeParam);
    }

}



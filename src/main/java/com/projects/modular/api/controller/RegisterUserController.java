package com.projects.modular.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.RegisterUser;
import com.projects.modular.api.model.params.RegisterUserParam;
import com.projects.modular.api.service.RegisterUserService;
import com.projects.modular.attendance.entity.Dept;


/**
 * 注册用户控制器
 *
 * @author demo

 */
@Controller
@RequestMapping("/registerUser")
public class RegisterUserController extends BaseController {

    private String PREFIX = "/modular/registerUser";

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
        return PREFIX + "/registerUser.html";
    }

    /**
     * 新增页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/registerUser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/registerUser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author demo
     * @Date 2022-03-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(RegisterUserParam registerUserParam) {
        this.registerUserService.add(registerUserParam);
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
    public ResponseData editItem(RegisterUserParam registerUserParam) {
        this.registerUserService.update(registerUserParam);
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
    public ResponseData delete(RegisterUserParam registerUserParam) {
        this.registerUserService.delete(registerUserParam);
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
    public ResponseData detail(RegisterUserParam registerUserParam) {
        RegisterUser detail = this.registerUserService.getById(registerUserParam.getId());
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
    public LayuiPageInfo list(RegisterUserParam registerUserParam) {
        return this.registerUserService.findPageBySpec(registerUserParam);
    }
    @RequestMapping("/getList")  
    @ResponseBody
    public ResponseData getList() {
    	 QueryWrapper<RegisterUser> objectQueryWrapper = new QueryWrapper<>();
    	
    	 List<RegisterUser> list = registerUserService.getBaseMapper().selectList(objectQueryWrapper);
    	 
        return ResponseData.success(list);
    }
    
    
    @PostMapping(value = "/registerUser")
	public ResponseData registerUser(@RequestBody RegisterUserParam registerUserParam) {

		try {
			QueryWrapper<RegisterUser> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("userName", registerUserParam.getName());
			RegisterUser registerUser = registerUserService.getOne(queryWrapper);
			if(null != registerUser){
				ResponseData.error("用户已注册");
			}
			
			registerUserService.add(registerUserParam);
			
			return ResponseData.success("注册成功");
		} catch (Exception e) {
			return ResponseData.error(e.getMessage());
		}

	}
    
    
    
    

}



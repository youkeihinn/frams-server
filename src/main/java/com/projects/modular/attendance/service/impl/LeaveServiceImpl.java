package com.projects.modular.attendance.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.core.shiro.ShiroKit;
import com.projects.modular.api.entity.RegisterUser;
import com.projects.modular.api.mapper.RegisterUserMapper;
import com.projects.modular.attendance.entity.Leave;
import com.projects.modular.attendance.mapper.LeaveMapper;
import com.projects.modular.attendance.model.params.LeaveParam;
import com.projects.modular.attendance.model.result.LeaveResult;
import  com.projects.modular.attendance.service.LeaveService;
import com.projects.modular.system.entity.User;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 请假管理 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {
	@Autowired
	private RegisterUserMapper  registerUserMapper;
    @Override
    public void add(LeaveParam param){
        Leave entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(LeaveParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(LeaveParam param){
        Leave oldEntity = getOldEntity(param);
        Leave newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public LeaveResult findBySpec(LeaveParam param){
        return null;
    }

    @Override
    public List<LeaveResult> findListBySpec(LeaveParam param){
        return null;
    }

    
    
    
    
    
    @Override
	public LayuiPageInfo findPageBySpec1(LeaveParam param) {
    	   Page pageContext = getPageContext();
           QueryWrapper<Leave> objectQueryWrapper = new QueryWrapper<>();
         	objectQueryWrapper.eq("user_id", param.getUserId());
           IPage page = this.page(pageContext, objectQueryWrapper);
           List<Leave> records = page.getRecords();
           List<LeaveResult>  list =new ArrayList<LeaveResult>();
           for (Leave leave : records) {
           	LeaveResult result  = new LeaveResult();
           	ToolUtil.copyProperties(leave, result);
           	RegisterUser user = registerUserMapper.selectById(leave.getUserId());      	
           	if(null !=user) {
           		result.setName(user.getName());
           	}
           	
           	list.add(result);
   		}
           page.setRecords(list);
           return LayuiPageFactory.createPageInfo(page);
	}

	@Override
    public LayuiPageInfo findPageBySpec(LeaveParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Leave> objectQueryWrapper = new QueryWrapper<>();
        if(ToolUtil.isNotEmpty(param.getUserId())) {
        	objectQueryWrapper.eq("user_id", param.getUserId());
        }
        if(ToolUtil.isNotEmpty(param.getTimes())) {
        	objectQueryWrapper.like("start_time", param.getTimes());
        }
        
        IPage page = this.page(pageContext, objectQueryWrapper);
        List<Leave> records = page.getRecords();
        List<LeaveResult>  list =new ArrayList<LeaveResult>();
        for (Leave leave : records) {
        	LeaveResult result  = new LeaveResult();
        	ToolUtil.copyProperties(leave, result);
          	RegisterUser user = registerUserMapper.selectById(leave.getUserId());      	
        	if(null !=user) {
        		result.setName(user.getName());
        	}
        	
        	list.add(result);
		}
        page.setRecords(list);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(LeaveParam param){
        return param.getLeaveId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Leave getOldEntity(LeaveParam param) {
        return this.getById(getKey(param));
    }

    private Leave getEntity(LeaveParam param) {
        Leave entity = new Leave();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

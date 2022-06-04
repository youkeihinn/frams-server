package com.projects.modular.attendance.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.AttendTime;
import com.projects.modular.attendance.mapper.AttendTimeMapper;
import com.projects.modular.attendance.model.params.AttendTimeParam;
import com.projects.modular.attendance.model.result.AttendTimeResult;
import  com.projects.modular.attendance.service.AttendTimeService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 公司考勤时间 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Service
public class AttendTimeServiceImpl extends ServiceImpl<AttendTimeMapper, AttendTime> implements AttendTimeService {

    @Override
    public void add(AttendTimeParam param){
        AttendTime entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(AttendTimeParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(AttendTimeParam param){
        AttendTime oldEntity = getOldEntity(param);
        AttendTime newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public AttendTimeResult findBySpec(AttendTimeParam param){
        return null;
    }

    @Override
    public List<AttendTimeResult> findListBySpec(AttendTimeParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(AttendTimeParam param){
        Page pageContext = getPageContext();
        QueryWrapper<AttendTime> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.orderByDesc("start_time");
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(AttendTimeParam param){
        return param.getTime();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private AttendTime getOldEntity(AttendTimeParam param) {
        return this.getById(getKey(param));
    }

    private AttendTime getEntity(AttendTimeParam param) {
        AttendTime entity = new AttendTime();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

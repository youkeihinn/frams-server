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
import com.projects.modular.attendance.entity.Dept;
import com.projects.modular.attendance.mapper.DeptMapper;
import com.projects.modular.attendance.model.params.DeptParam;
import com.projects.modular.attendance.model.result.DeptResult;
import  com.projects.modular.attendance.service.DeptService;

import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public void add(DeptParam param){
        Dept entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DeptParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(DeptParam param){
        Dept oldEntity = getOldEntity(param);
        Dept newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DeptResult findBySpec(DeptParam param){
        return null;
    }

    @Override
    public List<DeptResult> findListBySpec(DeptParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DeptParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Dept> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DeptParam param){
        return param.getDeptId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Dept getOldEntity(DeptParam param) {
        return this.getById(getKey(param));
    }

    private Dept getEntity(DeptParam param) {
        Dept entity = new Dept();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

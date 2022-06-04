package com.projects.modular.api.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.stylefeng.roses.core.util.ToolUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projects.core.common.page.LayuiPageFactory;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.api.entity.RegisterUser;
import com.projects.modular.api.mapper.RegisterUserMapper;
import com.projects.modular.api.model.params.RegisterUserParam;
import com.projects.modular.api.model.result.RegisterUserResult;
import com.projects.modular.api.service.RegisterUserService;

/**
 * <p>
 * 注册用户 服务实现类
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Service
public class RegisterUserServiceImpl extends ServiceImpl<RegisterUserMapper, RegisterUser> implements RegisterUserService {

    @Override
    public void add(RegisterUserParam param){
        RegisterUser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(RegisterUserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(RegisterUserParam param){
        RegisterUser oldEntity = getOldEntity(param);
        RegisterUser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public RegisterUserResult findBySpec(RegisterUserParam param){
        return null;
    }

    @Override
    public List<RegisterUserResult> findListBySpec(RegisterUserParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(RegisterUserParam param){
        Page pageContext = getPageContext();
        QueryWrapper<RegisterUser> objectQueryWrapper = new QueryWrapper<>();
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(RegisterUserParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private RegisterUser getOldEntity(RegisterUserParam param) {
        return this.getById(getKey(param));
    }

    private RegisterUser getEntity(RegisterUserParam param) {
        RegisterUser entity = new RegisterUser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}

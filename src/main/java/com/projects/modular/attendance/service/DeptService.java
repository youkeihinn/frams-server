package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.Dept;
import com.projects.modular.attendance.model.params.DeptParam;
import com.projects.modular.attendance.model.result.DeptResult;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
public interface DeptService extends IService<Dept> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-03-01
     */
    void add(DeptParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-03-01
     */
    void delete(DeptParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-03-01
     */
    void update(DeptParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-03-01
     */
    DeptResult findBySpec(DeptParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-03-01
     */
    List<DeptResult> findListBySpec(DeptParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-03-01
     */
     LayuiPageInfo findPageBySpec(DeptParam param);

}

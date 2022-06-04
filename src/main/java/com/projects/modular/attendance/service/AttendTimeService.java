package com.projects.modular.attendance.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projects.core.common.page.LayuiPageInfo;
import com.projects.modular.attendance.entity.AttendTime;
import com.projects.modular.attendance.model.params.AttendTimeParam;
import com.projects.modular.attendance.model.result.AttendTimeResult;

/**
 * <p>
 * 公司考勤时间 服务类
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
public interface AttendTimeService extends IService<AttendTime> {

    /**
     * 新增
     *
     * @author demo
     * @Date 2022-03-01
     */
    void add(AttendTimeParam param);

    /**
     * 删除
     *
     * @author demo
     * @Date 2022-03-01
     */
    void delete(AttendTimeParam param);

    /**
     * 更新
     *
     * @author demo
     * @Date 2022-03-01
     */
    void update(AttendTimeParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author demo
     * @Date 2022-03-01
     */
    AttendTimeResult findBySpec(AttendTimeParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author demo
     * @Date 2022-03-01
     */
    List<AttendTimeResult> findListBySpec(AttendTimeParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author demo
     * @Date 2022-03-01
     */
     LayuiPageInfo findPageBySpec(AttendTimeParam param);

}

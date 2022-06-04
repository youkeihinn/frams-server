package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 请假管理
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Data
public class LeaveParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long leaveId;

    /**
     * 员工
     */
    private Long userId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 审批回复
     */
    private String mark;

    /**
     * 审批状态
     */
    private Integer status;

    /**
     * 事由
     */
    private String reason;

    /**
     * 类型缺勤原因、出差时间、休假情况
     */
    private String type;
    
    
    private String times;

    @Override
    public String checkParam() {
        return null;
    }

}

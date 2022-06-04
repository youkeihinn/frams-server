package com.projects.modular.attendance.model.result;

import lombok.Data;
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
public class LeaveResult implements Serializable {

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
    
    private String name;

}

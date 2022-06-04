package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 请假管理
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@TableName("t_leave")
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "leave_id", type = IdType.ID_WORKER)
    private Long leaveId;

    /**
     * 员工
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 审批回复
     */
    @TableField("mark")
    private String mark;

    /**
     * 审批状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 事由
     */
    @TableField("reason")
    private String reason;

    /**
     * 类型缺勤原因、出差时间、休假情况
     */
    @TableField("type")
    private String type;


    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Leave{" +
        "leaveId=" + leaveId +
        ", userId=" + userId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", mark=" + mark +
        ", status=" + status +
        ", reason=" + reason +
        ", type=" + type +
        "}";
    }
}

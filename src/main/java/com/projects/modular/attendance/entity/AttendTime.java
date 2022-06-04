package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 公司考勤时间
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@TableName("t_attend_time")
public class AttendTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "time", type = IdType.ID_WORKER)
    private Long time;

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

    @TableField("address")
    private String address;
    @TableField("mark")
    private String mark;
    
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "AttendTime{" +
        "time=" + time +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        "}";
    }
}

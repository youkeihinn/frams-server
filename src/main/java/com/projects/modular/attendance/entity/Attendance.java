package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 员工考勤
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@TableName("t_attendance")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "attend_id", type = IdType.ID_WORKER)
    private Long attendId;

    /**
     * 考勤员工
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 考勤时间
     */
    @TableField("time")
    private Date time;

    /**
     * 考勤类型
     */
    @TableField("type")
    private String type;
    @TableField("pic")
    private String pic;
    @TableField("no")
    private String  no;
    @TableField("name")
    private String  name;
    @TableField("time_id")
    private Long timeId;
    @TableField("address")
    private String address;
    
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Attendance{" +
        "attendId=" + attendId +
        ", userId=" + userId +
        ", time=" + time +
        ", type=" + type +
        "}";
    }
}

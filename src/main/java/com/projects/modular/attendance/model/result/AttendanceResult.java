package com.projects.modular.attendance.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 员工考勤
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Data
public class AttendanceResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long attendId;

    /**
     * 考勤员工
     */
    private Long userId;

    /**
     * 考勤时间
     */
    private Date time;

    /**
     * 考勤类型
     */
    private String type;
    private String pic;
    private String  no;

    private String address;
    private String name;
}

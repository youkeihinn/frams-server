package com.projects.modular.attendance.model.result;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.projects.modular.attendance.entity.Attendance;

import lombok.Data;

/**
 * <p>
 * 公司考勤时间
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Data
public class AttendTimeResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long time;

    /**
     * 公司上班时间
     */
    private Date startTime;

    /**
     * 公司下班时间
     */
    private Date endTime;

    private String address;

    private String mark;
    
    private Long timeId;
    
    private List<Attendance> list ;
}

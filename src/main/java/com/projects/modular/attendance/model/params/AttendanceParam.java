package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

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
public class AttendanceParam implements Serializable, BaseValidatingParam {

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
    
    private String times;

    private String  no;

    private String  name;
    
    private Long timeId;
    private String address;
    private Long deptId;
    @Override
    public String checkParam() {
        return null;
    }

}

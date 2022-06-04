package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 公司考勤时间
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Data
public class AttendTimeParam implements Serializable, BaseValidatingParam {

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
    @Override
    public String checkParam() {
        return null;
    }

}

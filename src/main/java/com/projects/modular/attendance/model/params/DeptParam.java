package com.projects.modular.attendance.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Data
public class DeptParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门备注
     */
    private String mark;

    @Override
    public String checkParam() {
        return null;
    }

}

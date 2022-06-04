package com.projects.modular.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@TableName("t_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.ID_WORKER)
    private Long deptId;

    /**
     * 部门名称
     */
    @TableField("name")
    private String name;

    /**
     * 部门备注
     */
    @TableField("mark")
    private String mark;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Dept{" +
        "deptId=" + deptId +
        ", name=" + name +
        ", mark=" + mark +
        "}";
    }
}

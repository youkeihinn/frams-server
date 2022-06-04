package com.projects.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 年级管理表
 * </p>
 *
 * @author lv
 * @since 2019-05-22
 */
@TableName("byd_grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 年级ID
     */
    @TableId(value = "grade_id", type = IdType.ID_WORKER)
    private Long gradeId;

    /**
     * 年级名称
     */
    @TableField("grade_name")
    private String gradeName;

    /**
     * 学校ID
     */
    @TableField("school_id")
    private Long schoolId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 状态（年级）
     */
    @TableField("status")
    private String status;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Grade{" +
        "gradeId=" + gradeId +
        ", gradeName=" + gradeName +
        ", schoolId=" + schoolId +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", status=" + status +
        ", description=" + description +
        ", sort=" + sort +
        "}";
    }
}

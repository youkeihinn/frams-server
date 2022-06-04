package com.projects.modular.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 邮箱信息表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-05
 */
@TableName("email_info")
public class EmailInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 手机号
	 */
	@TableField("EMAIL")
	private String email;
	/**
	 * 短信验证码
	 */
	@TableField("VALIDATE_CODE")
	private String validateCode;
	/**
	 * 短信模板ID
	 */
	@TableField("TEMPLATE_CODE")
	private String templateCode;
	/**
	 * 回执ID,可根据该ID查询具体的发送状态
	 */
	@TableField("BIZID")
	private String bizid;
	/**
	 * 0=未发送，1=发送成功，2=发送失败
	 */
	@TableField("STATUS")
	private Integer status;
	/**
	 * 0=app，1=pc，2=其它
	 */
	@TableField("SOURCE")
	private Integer source;
	/**
	 * 失效时间
	 */
	@TableField("INVALID_TIME")
	private Date invalidTime;
	/**
	 * 修改时间
	 */
	@TableField("MODIFY_TIME")
	private Date modifyTime;
	/**
	 * 创建时间
	 */
	@TableField("CREATE_TIME")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

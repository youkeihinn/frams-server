package com.projects.modular.api.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 注册用户
 * </p>
 *
 * @author demo
 * @since 2022-03-01
 */
@Data
public class RegisterUserParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPass;
    
    private String juserPass;

    /**
     * 姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;
    private String headImage ;
    
   private String sex;
   private String adrress;
   private String tel;
   private String no;
    @Override
    public String checkParam() {
        return null;
    }

}

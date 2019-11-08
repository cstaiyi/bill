package com.zcit.bill.register.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zcit.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author huangpengyi
 * @since 2019-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_register")
public class Register extends BaseEntity<Register,String>{

    @TableField(exist = false)
    private String token;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    /**
     * 超级管理员，默认0为账本账号，1为超级管理员
     */
    private Integer root;

    /**
     * 账本名称
     */
    private String name;

    public static final String USER = "user";

    public static final String PASSWORD = "password";

    public static final String ROOT = "root";

    public static final String NAME = "name";
}

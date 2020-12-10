package org.fzb.account.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * member
 * @author 
 */
@Data
public class MemberDO implements Serializable {
    private Long id;

    private Long memberLevelId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 帐号启用状态:0->禁用；1->启用
     */
    private Integer status;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 头像
     */
    private String icon;

    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 城市
     */
    private String city;

    /**
     * 职业
     */
    private String job;

    /**
     * 个性签名
     */
    private String personalizedSignature;

    /**
     * 用户来源 1 小程序 2 公众号 3 页面
     */
    private Integer sourceType;

    private String avatar;

    private String weixinOpenid;

    private String invitecode;

    /**
     * 余额
     */
    private Long blance;

    private Long schoolId;

    private Long areaId;

    private String schoolName;

    private String areaName;

    private Integer buyCount;

    private Long buyMoney;

    private String memberLevelName;

    private static final long serialVersionUID = 1L;
}
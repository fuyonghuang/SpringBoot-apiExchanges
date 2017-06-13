package com.sic.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created  on 2017/6/13.
 */
@Data
public class User {
    private String id;

    private String companyId;

    private String officeId;

    private String loginName;

    private String no;

    private String name;

    private String email;

    private String phone;

    private String mobile;

    private String userType;

    private String photo;

    private String loginIp;

    private String loginDate;

    private String loginFlag;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String password;
}

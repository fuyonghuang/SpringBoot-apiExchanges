package com.sic.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ChTaskRecord {
    private String id;

    private String chTaskRecordId;

    private Long logicalType;

    private String taskId;

    private String officeId;

    private String fillerId;

    private String content;

    private String fileIds;

    private String passFlag;

    private Long score;

    private String uploadBy;

    private String uploadOfficeId;

    private Date uploadDate;

    private String uploadPosition;

    private Long uploadTool;

    private String deviceNumber;

    private String readBy;

    private Date readDate;

    private String auditBy;

    private Date auditDate;

    private String auditOpinion;

    private String createBy;

    private String createOfficeId;

    private Date createDate;

    private String updateBy;

    private String updateOfficeId;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String systemFlag;

    private Long status;

    private String procInsId;

    private Long reformStatus;

    private String isSync;

    private String isCity;

    private SysOffice office;


}
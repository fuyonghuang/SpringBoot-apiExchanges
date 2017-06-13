package com.sic.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class ChTask {
    private String id;

    private String chTaskId;

    private String subFlag;

    private Long subNum;

    private String superId;

    private String parentId;

    private String taskRecordId;

    private Long type;

    private String name;

    private String fillFormId;

    private String statisticsFormId;

    private String content;

    private String fileIds;

    private Long fillerType;

    private Date startDate;

    private Date finishDate;

    private Long frequencyNum;

    private Long frequencyUnit;

    private Long durationNum;

    private Long durationUnit;

    private Date informDate;

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

    private String reformNumber;

    private String nextStartDate;

    private String nextFinishDate;

    private Long auditLevel;

    private Long autoAuditNum;

    private User user;
    private ChTaskRecord taskRecord;

    private SysOffice createOffice;

}
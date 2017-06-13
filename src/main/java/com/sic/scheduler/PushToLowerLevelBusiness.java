package com.sic.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sic.entity.*;
import com.sic.service.*;
import com.sic.utils.HttpService;
import com.sic.utils.configuration.ServiceProperties;
import com.sic.utils.exception.NullResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 市级下发给下级任务 推送到下级
 */
@Component
public class PushToLowerLevelBusiness {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger( this.getClass() );
    private ServiceProperties serviceProperties;
    private HttpService httpService;
    private ChTaskService chTaskService;
    private ChTaskRecordService chTaskRecordService;
    private ChFormService chFormService;
    private ChItemService chItemService;
    private ChItemRecordService chItemRecordService;
    private ChItemRequirementService chItemRequirementService;
    private ChFormItemService chFormItemService;
    @Autowired
     PushToLowerLevelBusiness(ServiceProperties serviceProperties, HttpService httpService, ChTaskService chTaskService, ChTaskRecordService chTaskRecordService, ChFormService chFormService, ChItemService chItemService, ChItemRecordService chItemRecordService, ChItemRequirementService chItemRequirementService, ChFormItemService chFormItemService) {
        this.serviceProperties = serviceProperties;
        this.httpService = httpService;
        this.chTaskService = chTaskService;
        this.chTaskRecordService = chTaskRecordService;
        this.chFormService = chFormService;
        this.chItemService = chItemService;
        this.chItemRecordService = chItemRecordService;
        this.chItemRequirementService = chItemRequirementService;
        this.chFormItemService = chFormItemService;
    }

    /**
     * 市;推送到区
     * 获取下发到下级组织的任务，推送给下级组织
     */
    public void PushInsertToLowerLevel() {

        CheckDataModel bodyList = new CheckDataModel();
        ChTaskRecord chTaskRecord = new ChTaskRecord();
        chTaskRecord.setStatus( 1L );
        chTaskRecord.setOfficeId( "866c730fcb5a415aad2b48ff618e07c81486901270569" );
        List<ChTaskRecord> chTaskRecordList = chTaskRecordService.select( chTaskRecord );
        for (ChTaskRecord chTaskRecordbean : chTaskRecordList) {
            bodyList.setChTaskRecord( chTaskRecordbean );
            ChTask chTask = chTaskService.selectById( chTaskRecordbean.getTaskId() );
            bodyList.setChTask( chTask );
            List<ChItemRecord> chItemRecord = chItemRecordService.selectByTaskRecordId( chTaskRecordbean.getId() );
            bodyList.setChItemRecordList( chItemRecord );
            List<ChItemRequirement> chItemRequirement = chItemRequirementService.selectByTaskId( chTaskRecordbean.getTaskId() );
            bodyList.setChItemRequirementList( chItemRequirement.size()==0?new ArrayList<>(  ):chItemRequirement );
            //FillFormId 任务下发时任务对应的表单ID ，当该字段为空时，该任务不为安检类任务，不需要抽取 表单(ch_form ch_item ...)信息
            if (chTask.getFillFormId()!=null){
                ChForm chForm = chFormService.selectById( chTask.getFillFormId() );
                if (chForm!=null) {
                    bodyList.setChForm( chForm );
                    List<ChFormItem> chFormItemList = chFormItemService.selectByFormId( chForm.getId() );
                    bodyList.setChFormItemList( chFormItemList.size() == 0 ? new ArrayList<>() : chFormItemList );
                    List<ChItem> chItemList = chItemService.selectByFormId( chForm.getId() );
                    bodyList.setChItemList( chItemList.size() == 0 ? new ArrayList<>() : chItemList );
                }
            }
            ServiceProperties.ApiProperty getApiSerivce = serviceProperties.getApi( "gettest" );
            MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();


            HttpEntity<CheckDataModel> httpEntity;
            httpEntity = new HttpEntity<>( bodyList, headerMap );
            String responseBody;
            try {
               System.out.println( objectMapper.writeValueAsString(bodyList));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            try {
                //post put 请求时 urlParams 设置为 null
              /*  responseBody = httpService.request( getApiSerivce, httpEntity, null );
                System.out.println(responseBody);
                String errorCode = objectMapper.readTree(responseBody).get("errorCode").textValue();
                if (errorCode != null || errorCode.equals("0")) {
                    ChTaskRecord bean = new ChTaskRecord();
                    bean.setIsSync( "1" );
                    bean.setId( chTaskRecordbean.getId() );
                    chTaskRecordService.updateByIsSync( bean );

                } else {
                    throw new NullResultException();
                }*/


            } catch (NullResultException e) {
                logger.debug( e.toString() );
            }
        }
    }

    /**
     * 下发到下级的任务更新推送
     */
    public void  PushUpdateToLowerLevel(){
        CheckDataModel bodyList = new CheckDataModel();
        ChTaskRecord chTaskRecord = new ChTaskRecord();
        chTaskRecord.setStatus( 1L );
        chTaskRecord.setOfficeId( "866c730fcb5a415aad2b48ff618e07c81486901270569" );
        List<ChTaskRecord> chTaskRecordList = chTaskRecordService.select( chTaskRecord );
        for (ChTaskRecord chTaskRecordbean : chTaskRecordList) {
            bodyList.setChTaskRecord( chTaskRecordbean );
            ServiceProperties.ApiProperty getApiSerivce = serviceProperties.getApi( "updateCheckRecord" );
            MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
            //配置header参数
            // headerMap.add("COOKIE", "");

            HttpEntity<CheckDataModel> httpEntity;
            httpEntity = new HttpEntity<>( bodyList, headerMap );
            String responseBody;
            try {
                //post put 请求时 urlParams 设置为 null
                responseBody = httpService.request( getApiSerivce, httpEntity, null );
                System.out.println(responseBody);

//                ChTaskRecord bean = new ChTaskRecord();
//                bean.setIsSync( "1" );
//                bean.setId( chTaskRecordbean.getId() );
//                chTaskRecordService.updateByIsSync( bean );

            } catch (NullResultException e) {
                logger.debug( e.toString() );
            } catch (IOException e) {
                logger.debug( e.toString() );
            }
        }
    }

}

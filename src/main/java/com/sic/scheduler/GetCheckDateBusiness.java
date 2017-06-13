package com.sic.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sic.entity.ChItemRecord;
import com.sic.entity.ChItemRequirement;
import com.sic.entity.ChTaskRecord;
import com.sic.service.*;
import com.sic.utils.HttpService;
import com.sic.utils.configuration.ServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 *
 */
@Component
public class GetCheckDateBusiness {
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
    GetCheckDateBusiness(ServiceProperties serviceProperties, HttpService httpService, ChTaskService chTaskService, ChTaskRecordService chTaskRecordService, ChFormService chFormService, ChItemService chItemService, ChItemRecordService chItemRecordService, ChItemRequirementService chItemRequirementService, ChFormItemService chFormItemService) {
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
     * 区：
     * 获取市级级下发的，表单已提交的安检任务
     * 组装好相关答案同步到市
     * 组装好相关附件同步到市
     */
    public void getTaskItemRecords() {
    	// 拉取数据条件 sync='0 and status='7' 后，答案提交到市的接口中同时更新市的状态值 sync='0' 、 同时更新状态值 sync='1' .
		try {
			ChTaskRecord chTaskRecord = new ChTaskRecord();
			chTaskRecord.setStatus(8L);
			List<ChItemRecord> chItemRecords = chItemRecordService.select(chTaskRecord);
			System.out.println(chItemRecords);

//			if (chItemRecords != null && !chItemRecords.isEmpty()) {
				MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
				paramsMap.add("SYNCAREA", "HP");
				
				ServiceProperties.ApiProperty getAttachmentSerivce = serviceProperties.getApi("getTaskItemRecords");
				
				HttpEntity<List<ChItemRecord>> httpEntity = new HttpEntity<>(chItemRecords);
				String responseBody = httpService.request(getAttachmentSerivce, httpEntity, paramsMap);
				// 反序列化
				String chItemRequirement = objectMapper.readTree(responseBody).get("chItemRequirement").toString();
				List<ChItemRequirement> a = objectMapper.readValue(chItemRequirement,
						objectMapper.getTypeFactory().constructCollectionType(List.class, ChItemRequirement.class));
				
				System.out.print(a);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

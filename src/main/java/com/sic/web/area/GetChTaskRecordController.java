package com.sic.web.area;

import com.sic.entity.*;
import com.sic.service.*;
import com.sic.utils.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuyong_huang on 2017/6/8.
 */
@RestController
public class GetChTaskRecordController {

    private final Logger logger = LoggerFactory.getLogger( this.getClass() );
    private ChTaskService chTaskService;
    private ChTaskRecordService chTaskRecordService;
    private ChFormService chFormService;
    private ChItemService chItemService;
    private ChItemRecordService chItemRecordService;
    private ChItemRequirementService chItemRequirementService;
    private ChFormItemService chFormItemService;


    @Autowired
    GetChTaskRecordController(ChTaskService chTaskService, ChTaskRecordService chTaskRecordService, ChFormService chFormService, ChItemService chItemService, ChItemRecordService chItemRecordService, ChItemRequirementService chItemRequirementService, ChFormItemService chFormItemService) {
        this.chTaskService = chTaskService;
        this.chTaskRecordService = chTaskRecordService;
        this.chFormService = chFormService;
        this.chItemService = chItemService;
        this.chItemRecordService = chItemRecordService;
        this.chItemRequirementService = chItemRequirementService;
        this.chFormItemService = chFormItemService;
    }

    /**
     * 上级下发任务接收端口
     *
     * @param checkDataModel
     * @return
     */
    @PostMapping("insertCheckRecord")
    public Map insertCheck(@RequestBody CheckDataModel checkDataModel) {
        System.out.println( "妖兽啦" );
        Map<String, String> response = new HashMap<>();
        try {
            ChTaskRecord chTaskRecord = checkDataModel.getChTaskRecord();
            ChTask chTask = checkDataModel.getChTask();
            ChForm chForm = checkDataModel.getChForm();
            List<ChItem> chItemList = checkDataModel.getChItemList();
            List<ChFormItem> chFormItemList = checkDataModel.getChFormItemList();
            List<ChItemRequirement> chItemRequirementList = checkDataModel.getChItemRequirementList();
            List<ChItemRecord> chItemRecordList = checkDataModel.getChItemRecordList();
            chTaskRecord.setProcInsId( "" );
            int i = chTaskRecordService.insert( chTaskRecord );
            if (i != 0) {
                chTaskService.insert( chTask );
                chFormService.insert( chForm );
                chItemService.insert( chItemList );
                chFormItemService.insert( chFormItemList );
                chItemRequirementService.insert( chItemRequirementList );
                chItemRecordService.insert( chItemRecordList );
                response.put( "errorCode", "0" );
                response.put( "errorMsg", "同步成功" );
            } else {
                response.put( "errorCode", "10000" );
                response.put( "errorMsg", "记录已经同步" );
            }
        } catch (Exception e) {

            logger.info( e.toString() );
            throw new CustomException( "错误", "-1" );
        }
        return response;
    }

    /**
     * 上级任务更新推送接收
     * @param checkDataModel
     * @return
     */
    @PutMapping("/updateCheckRecord")
    public Map updateCheck(@RequestBody  CheckDataModel checkDataModel){
        try {
            ChTaskRecord chTaskRecord = checkDataModel.getChTaskRecord();

            chTaskRecordService.updateById(chTaskRecord);


        }catch (Exception e){

        }
        return  new HashMap(  );
    }

}

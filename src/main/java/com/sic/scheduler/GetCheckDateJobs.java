package com.sic.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fuyong_huang on 2017/6/8.
 */
@Component
public class GetCheckDateJobs  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GetCheckDateBusiness getCheckDateBusinesset;
    private PushToLowerLevelBusiness pushToLowerLevelBusiness;
    @Autowired
    public GetCheckDateJobs(GetCheckDateBusiness getCheckDateBusinesset, PushToLowerLevelBusiness pushToLowerLevelBusiness) {
        this.getCheckDateBusinesset = getCheckDateBusinesset;
        this.pushToLowerLevelBusiness = pushToLowerLevelBusiness;
    }




  //  @Scheduled(cron="0 0/1 * * * ?")
    public void   getChTaskRecordDateJobs(){
        logger.info( "跑起来吧皮卡丘" );
        pushToLowerLevelBusiness.PushUpdateToLowerLevel();
    }
}

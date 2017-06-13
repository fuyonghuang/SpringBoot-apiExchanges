package com.sic.service.impl;

import com.sic.entity.ChItemRecord;
import com.sic.entity.ChTaskRecord;
import com.sic.entity.FtpFileSync;
import com.sic.mapper.ChItemRecordMapper;
import com.sic.mapper.FtpFileMapper;
import com.sic.mapper.FtpFileSyncMapper;
import com.sic.service.ChItemRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChItemRecordServiceImpl implements ChItemRecordService {
	private final Logger logger = LoggerFactory.getLogger( this.getClass() );
	@Autowired
	private ChItemRecordMapper chItemRecordMapper;
	@Autowired
	private FtpFileMapper ftpFileMapper;
	@Autowired
	private FtpFileSyncMapper ftpFileSyncMapper;
	
	@Override
	public List<ChItemRecord> select(ChTaskRecord chTaskRecord) {
		// TODO Auto-generated method stub
		return chItemRecordMapper.select(chTaskRecord);
	}

	@Override
	@Transactional
	public int insert(List<ChItemRecord> chItemRecordList) {
		int i=0;
		for (ChItemRecord bean:chItemRecordList){
			if(chItemRecordMapper.selectByPrimaryKey( bean.getId())==null) {
				i = chItemRecordMapper.insert( bean );
			}else{
				logger.info( "ChItemRecord表中该记录已经存在 id:"+bean.getId() );
			}

		}
			return i;
	}

	@Override
	@Transactional
	public int update(List<ChItemRecord> chItemRecordList) {
		int i=0;
		for (ChItemRecord bean:chItemRecordList) {
			List<FtpFileSync> ftpFileList = null;
			if ((ftpFileList = bean.getFtpFileList()) != null && !ftpFileList.isEmpty()) {
				ftpFileMapper.insertList(bean.getFtpFileList());
				ftpFileSyncMapper.insertList(bean.getFtpFileList());
			}
			i = chItemRecordMapper.update( bean );
		}
			return i;
	}

	@Override
	public List<ChItemRecord> selectByTaskRecordId(String taskRecordId) {
		return chItemRecordMapper.selectByTaskRecordId(taskRecordId);
	}

}

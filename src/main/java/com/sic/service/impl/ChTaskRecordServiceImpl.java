package com.sic.service.impl;

import com.sic.entity.ChTaskRecord;
import com.sic.mapper.ChTaskRecordMapper;
import com.sic.service.ChTaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChTaskRecordServiceImpl implements ChTaskRecordService {

	@Autowired
	private  ChTaskRecordMapper chTaskRecordMapper;
	
	@Override
	public List<ChTaskRecord> select(ChTaskRecord chTaskRecord) {
		// TODO Auto-generated method stub
		return chTaskRecordMapper.select(chTaskRecord);
	}

	@Override
	@Transactional
	public int insert(ChTaskRecord chTaskRecord) {
		int i=0;
		//设置isCity 的状态为来至市下发的任务
		chTaskRecord.setIsCity( "1" );
		if(chTaskRecordMapper.selectById(chTaskRecord.getId())==null) {
			i = chTaskRecordMapper.insert( chTaskRecord );
		}
		return i;
	}
	@Override
	public int updateByIsSync(ChTaskRecord record) {
		return chTaskRecordMapper.updateByIsSync( record );
	}

	@Override
	public int updateById(ChTaskRecord chTaskRecord) {
		return 0;
	}
}

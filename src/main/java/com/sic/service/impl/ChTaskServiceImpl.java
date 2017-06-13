package com.sic.service.impl;

import com.sic.entity.ChTask;
import com.sic.entity.ChTaskRecord;
import com.sic.mapper.ChTaskMapper;
import com.sic.service.ChTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ChTaskServiceImpl implements ChTaskService {
	private final Logger logger = LoggerFactory.getLogger( this.getClass() );
	@Autowired
	private ChTaskMapper chTaskMapper;


	
	@Override
	public List<ChTask> select(ChTaskRecord chTaskRecord) {
		// TODO Auto-generated method stub
		return chTaskMapper.select(chTaskRecord);
	}

	@Override
	@Transactional
	public int insert(ChTask record) {
		int i=0;
			if(chTaskMapper.selectById(record.getId())==null){
				i=chTaskMapper.insert(record);
			}else{
			logger.info( "该记录已经存在不需要更新" );
			}
		return i;
	}

	@Override
	public ChTask selectById(String id) {
		return chTaskMapper.selectById(id);
	}

}

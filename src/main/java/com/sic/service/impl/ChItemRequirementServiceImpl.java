package com.sic.service.impl;

import com.sic.entity.ChItemRequirement;
import com.sic.entity.ChTaskRecord;
import com.sic.mapper.ChItemRequirementMapper;
import com.sic.service.ChItemRequirementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ChItemRequirementServiceImpl implements ChItemRequirementService {
	private  final Logger logger = LoggerFactory.getLogger( this.getClass() );
	@Autowired
	private ChItemRequirementMapper chItemRequirementMapper;
	@Override
	public List<ChItemRequirement> select(ChTaskRecord chTaskRecord) {
		// TODO Auto-generated method stub
		return chItemRequirementMapper.select(chTaskRecord);
	}

	@Override
	@Transactional
	public int insert(List<ChItemRequirement> chItemRequirementList) {
		int i=0;
		for (ChItemRequirement bean:chItemRequirementList ) {
			if (chItemRequirementMapper.selectById( bean.getId() ) == null) {
				chItemRequirementMapper.insert( bean );
			} else {
				logger.info( "ChItemRequirement 该记录已经存在 ID" + bean.getId() );
			}
		}
		return i;
	}

	@Override
	public List<ChItemRequirement> selectByTaskId(String taskId) {
		return chItemRequirementMapper.selectByTaskId( taskId );
	}

}

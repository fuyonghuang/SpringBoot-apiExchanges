package com.sic.service.impl;

import com.sic.entity.ChFormItem;
import com.sic.entity.ChTaskRecord;
import com.sic.mapper.ChFormItemMapper;
import com.sic.service.ChFormItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChFormItemServiceImpl implements ChFormItemService {
	private final Logger logger = LoggerFactory.getLogger( this.getClass() );
	@Autowired
	private ChFormItemMapper chFormItemMapper;
	
	@Override
	public List<ChFormItem> select(ChTaskRecord chTaskRecord) {
		// TODO Auto-generated method stub
		return chFormItemMapper.select(chTaskRecord);
	}

	@Override
	@Transactional
	public int insert(List<ChFormItem> chFormItemList) {
		int i=0;
		for(ChFormItem bean:chFormItemList){
			if(chFormItemMapper.selectByPrimaryKey( bean.getId() )==null){
				i=	chFormItemMapper.insert( bean );
			}else{
			logger.info( "chFormItem 中该记录已经存在 ID"+bean.getId() );
			}
		}
		return i;
	}

	@Override
	public List<ChFormItem> selectByFormId(String id) {
		return chFormItemMapper.selectByFormId(id);
	}

}

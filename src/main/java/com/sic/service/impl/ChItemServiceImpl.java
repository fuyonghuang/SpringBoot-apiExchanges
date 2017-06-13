package com.sic.service.impl;

import com.sic.entity.ChItem;
import com.sic.entity.ChTaskRecord;
import com.sic.mapper.ChItemMapper;
import com.sic.service.ChItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChItemServiceImpl implements ChItemService {
	private final Logger logger = LoggerFactory.getLogger( this.getClass() );
	@Autowired
    private ChItemMapper chItemMapper;
	@Override
	public List<ChItem> select(ChTaskRecord chTaskRecord) {
		// TODO Auto-generated method stub
		return chItemMapper.select(chTaskRecord);
	}

	@Override
    @Transactional
	public int insert(List<ChItem> chItemList) {
		int i=0;
		for (ChItem bean:chItemList){
			//判断chItem 记录是否存在，存在则不插入
			if(chItemMapper.selectByPrimaryKey(bean.getId())==null) {
				i = chItemMapper.insert( bean );
			}else {
                logger.info( "chitem 该记录已经存在 id"+bean.getId() );
			}
		}
		return i;
	}

	@Override
	public List<ChItem> selectByFormId(String id) {
		return chItemMapper.selectByFormId(id);
	}

}

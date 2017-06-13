package com.sic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sic.entity.SysOffice;
import com.sic.mapper.SysOfficeMapper;
import com.sic.service.SysOfficeService;
@Service
public class SysOfficeServiceImpl implements SysOfficeService {

	@Autowired
	private SysOfficeMapper sysOfficeMapper;
	
	public List<SysOffice> selectByEntity(SysOffice sysOffice) {
		return sysOfficeMapper.selectByEntity(sysOffice);
	}

}

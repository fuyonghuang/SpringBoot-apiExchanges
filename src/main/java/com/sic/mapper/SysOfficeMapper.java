package com.sic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sic.entity.SysOffice;
@Mapper
public interface SysOfficeMapper {
	
    List<SysOffice> selectByEntity(SysOffice sysOffice);

}
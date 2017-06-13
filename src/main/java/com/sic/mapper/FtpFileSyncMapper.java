package com.sic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sic.entity.FtpFileSync;

@Mapper
public interface FtpFileSyncMapper {
    int deleteByPrimaryKey(String id);

	int insert(FtpFileSync record);

	int insertList(List<FtpFileSync> list);

	FtpFileSync selectByPrimaryKey(String id);


	int updateByPrimaryKey(FtpFileSync record);
}
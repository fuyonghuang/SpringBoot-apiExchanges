package com.sic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sic.entity.FtpFile;
import com.sic.entity.FtpFileSync;

@Mapper
public interface FtpFileMapper {
    int deleteByPrimaryKey(String id);

	int insert(FtpFile record);

	int insertList(List<FtpFileSync> list);

	FtpFile selectByPrimaryKey(String id);


	int updateByPrimaryKey(FtpFile record);
}
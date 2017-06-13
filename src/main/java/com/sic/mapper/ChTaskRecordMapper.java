package com.sic.mapper;

import com.sic.entity.ChTaskRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChTaskRecordMapper {

    int insert(ChTaskRecord record);

    List<ChTaskRecord> select(ChTaskRecord chTaskRecord);

    int updateByIsSync(ChTaskRecord record);

    ChTaskRecord selectById(String id);
}
package com.sic.mapper;

import com.sic.entity.ChItemRecord;
import com.sic.entity.ChTaskRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChItemRecordMapper {

    int insert(ChItemRecord record);
    int update(ChItemRecord record);

    List<ChItemRecord> select(ChTaskRecord chTaskRecord);

    ChItemRecord selectByPrimaryKey(String parma);

    List<ChItemRecord> selectByTaskRecordId(String taskRecordId);
}
package com.sic.mapper;

import com.sic.entity.ChTask;
import com.sic.entity.ChTaskRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChTaskMapper {

    int insert(ChTask record);

    List<ChTask> select(ChTaskRecord chTaskRecord);
    ChTask selectById(String id);
}
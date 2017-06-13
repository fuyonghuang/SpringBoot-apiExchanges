package com.sic.mapper;

import com.sic.entity.ChForm;
import com.sic.entity.ChTaskRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChFormMapper {
   
    int insert(ChForm record);

    List<ChForm> select(ChTaskRecord chTaskRecord);

    ChForm selectById(String id);
}
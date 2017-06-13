package com.sic.mapper;

import com.sic.entity.ChItem;
import com.sic.entity.ChTaskRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChItemMapper {

    int insert(ChItem record);
    
    List<ChItem> select(ChTaskRecord chTaskRecord);
    ChItem selectByPrimaryKey(String id);

    List<ChItem> selectByFormId(String id);
}
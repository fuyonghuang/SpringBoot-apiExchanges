package com.sic.mapper;

import com.sic.entity.ChFormItem;
import com.sic.entity.ChTaskRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChFormItemMapper {

    int insert(ChFormItem record);
    
    List<ChFormItem> select(ChTaskRecord chTaskRecord);

    ChFormItem selectByPrimaryKey(String id);

    List<ChFormItem> selectByFormId(String id);
}
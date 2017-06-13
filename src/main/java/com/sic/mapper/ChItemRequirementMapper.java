package com.sic.mapper;

import com.sic.entity.ChItemRequirement;
import com.sic.entity.ChTaskRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ChItemRequirementMapper {
    int insert(ChItemRequirement record);
    ChItemRequirement selectById(String id);
    List<ChItemRequirement> select(ChTaskRecord chTaskRecord);
    List<ChItemRequirement> selectByTaskId(String taskId);
}
package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.ItCatory;
import java.util.List;

public interface ItCatoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItCatory record);

    ItCatory selectByPrimaryKey(Long id);

    List<ItCatory> selectAll();

    int updateByPrimaryKey(ItCatory record);
}
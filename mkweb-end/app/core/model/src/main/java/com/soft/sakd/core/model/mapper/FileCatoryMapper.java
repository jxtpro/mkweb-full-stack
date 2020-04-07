package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.FileCatory;
import java.util.List;

public interface FileCatoryMapper {
  int deleteByPrimaryKey(Long id);

  int insert(FileCatory record);

  FileCatory selectByPrimaryKey(Long id);

  List<FileCatory> selectAll();

  int updateByPrimaryKey(FileCatory record);
}

package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.SResource;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SResourceMapper {
  int deleteByPrimaryKey(Long id);

  int insert(SResource record);

  SResource selectByPrimaryKey(Long id);

  List<SResource> selectAll();

  int updateByPrimaryKey(SResource record);

  SResource findByName(String name);

  List<SResource> findByLimit(@Param("pageSize") int pageSize, @Param("page") int page);

  List<SResource> findByKeyWordLimit(@Param("keyWord") String keyWord, @Param("pageSize") int pageSize, @Param("page") int page);
}

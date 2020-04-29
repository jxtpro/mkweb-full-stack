package com.soft.sakd.common.facade;

import com.soft.sakd.core.model.entity.SResource;
import java.util.List;
import lombok.Synchronized;

public interface ResourcesService {

  // 所有的资源
   List<SResource>  findByLimit(int pageSize, int page);

  // 保存
  int save(SResource resources);

  SResource findByName(String name);

  void insert(SResource entity1);

  List<SResource> selectAll();

  //分页查询 keyword
  List<SResource> findByKeyWordLimit(String keyWord, int pageSize, int page);
}

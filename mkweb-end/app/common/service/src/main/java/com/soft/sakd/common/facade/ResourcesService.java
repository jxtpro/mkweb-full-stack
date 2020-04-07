package com.soft.sakd.common.facade;

import com.soft.sakd.core.model.entity.SResource;
import java.util.List;

public interface ResourcesService {

  // 所有的资源
  List<SResource> findAll();

  // 保存
  int save(SResource resources);
}

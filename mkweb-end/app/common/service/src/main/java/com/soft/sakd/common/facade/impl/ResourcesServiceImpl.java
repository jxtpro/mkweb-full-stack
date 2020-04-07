package com.soft.sakd.common.facade.impl;

import com.soft.sakd.common.facade.ResourcesService;
import com.soft.sakd.core.model.entity.SResource;
import com.soft.sakd.core.model.mapper.SResourceMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

  private final SResourceMapper sResourceMapper;

  @Override
  public List<SResource> findAll() {
    return sResourceMapper.selectAll();
  }

  @Override
  public int save(SResource resources) {
    return sResourceMapper.insert(resources);
  }
}

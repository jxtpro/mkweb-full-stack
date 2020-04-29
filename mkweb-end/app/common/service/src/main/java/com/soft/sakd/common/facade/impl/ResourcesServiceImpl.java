package com.soft.sakd.common.facade.impl;

import com.soft.sakd.common.facade.ResourcesService;
import com.soft.sakd.core.model.entity.SResource;
import com.soft.sakd.core.model.mapper.SResourceMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

  @Autowired private final SResourceMapper sResourceMapper;

  @Override
  public List<SResource> findByLimit(int pageSize, int page) {
    return sResourceMapper.findByLimit(pageSize, page);
  }

  @Override
  public int save(SResource resources) {
    return sResourceMapper.insert(resources);
  }

  @Override
  public SResource findByName(String name) {
    return sResourceMapper.findByName(name);
  }

  @Override
  public void insert(SResource entity1) {
    sResourceMapper.insert(entity1);
  }

  @Override
  public List<SResource> selectAll() {
    return sResourceMapper.selectAll();
  }

  @Override
  public List<SResource> findByKeyWordLimit(String keyWord, int pageSize, int page) {
    return sResourceMapper.findByKeyWordLimit(keyWord, pageSize, page);
  }
}

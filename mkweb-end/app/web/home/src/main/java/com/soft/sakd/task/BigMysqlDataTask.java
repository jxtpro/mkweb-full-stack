package com.soft.sakd.task;

import com.soft.sakd.common.facade.ResourcesService;
import com.soft.sakd.core.model.entity.SResource;
import com.soft.sakd.core.model.mapper.SResourceMapper;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author xujie
 * @since 2020/1/12 11:53
 */
public class BigMysqlDataTask implements Callable<List<SResource>> {

  private final int pageSize;
  private final int page;
  private final ResourcesService resourcesService;

  public BigMysqlDataTask(int pageSize, int page,  ResourcesService resourcesService) {
    this.page = page;
    this.pageSize = pageSize;
    this.resourcesService = resourcesService;
  }

  @Override
  public List<SResource> call() throws Exception {
    return resourcesService.findByLimit(pageSize,page);
  }
}

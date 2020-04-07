package com.soft.sakd.task;

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
  private final SResourceMapper sResourceMapper;

  public BigMysqlDataTask(int page, int pageSize, SResourceMapper sResourceMapper) {
    this.page = page;
    this.pageSize = pageSize;
    this.sResourceMapper = sResourceMapper;
  }

  @Override
  public List<SResource> call() throws Exception {
    System.out.println("thread name : " + Thread.currentThread().getName());
    return sResourceMapper.findByLimit(page, pageSize);
  }
}

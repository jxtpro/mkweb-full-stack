package com.soft.sakd.biz.mange.impl;

import com.soft.sakd.biz.mange.ItCatoryMange;
import com.soft.sakd.common.facade.ItCatoryService;
import com.soft.sakd.common.search.bean.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujie
 * @since 2020/4/2 12:45
 */
@Log4j2
@Service
public class ItCatoryMangeImpl implements ItCatoryMange {
  @Autowired private ItCatoryService itCatoryService;

  @Override
  public Result queryList() {
    return Result.makeSuccessResult(itCatoryService.queryList());
  }
}

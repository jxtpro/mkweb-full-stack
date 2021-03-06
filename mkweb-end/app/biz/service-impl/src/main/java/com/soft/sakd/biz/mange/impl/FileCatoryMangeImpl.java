package com.soft.sakd.biz.mange.impl;

import com.soft.sakd.biz.mange.FileCatoryMange;
import com.soft.sakd.common.exception.ServiceException;
import com.soft.sakd.common.facade.FileCatoryService;
import com.soft.sakd.common.search.bean.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujie
 * @since 2020/4/2 11:20
 */
@Service
@Log4j2
public class FileCatoryMangeImpl implements FileCatoryMange {

  @Autowired private FileCatoryService articleFileCatoryService;

  @Override
  public Result queryList() {
    return Result.makeSuccessResult(articleFileCatoryService.queryList());
  }
}

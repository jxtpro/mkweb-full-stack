package com.soft.sakd.controller;

import com.soft.sakd.biz.mange.FileCatoryMange;
import com.soft.sakd.common.search.bean.SearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujie
 * @since 2020/4/2 0:59
 */
@Log4j2
@RequiredArgsConstructor
@RestController
public class FileCatoryController {
  @Autowired public FileCatoryMange fileCatoryMange;

  @RequestMapping("/fileCatory/queryList")
  public SearchResult queryList() {
    return fileCatoryMange.queryList();
  }
}

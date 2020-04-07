/*
 * Copyright (c) 2011-2019, Xu Jie 徐杰 (jxtpro@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 */
package com.soft.sakd.common.search.bean;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/** 搜索结果 */
@Getter
@Setter
public class SearchResult<E> {
  protected List<E> list;
  protected int totalCount;
  private Map<String, Object> params;
  private Object data;
  private String msg;
  private boolean status;

  public SearchResult() {
    super();
    setStatus(true);
  }

  public SearchResult(List<E> result, int totalCount) {
    this();
    setList(result);
    setTotalCount(totalCount);
  }
}

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

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/** 搜索条件 */
@Getter
@Setter
@Log4j2
public class SearchCondition<Entity> {
  private Date startDate;
  private Date endDate;
  private Integer isDeleted = 0; // 未删除
  protected int page;
  protected int pageSize = 10;
  protected String sort = "id";
  protected boolean acending;
  private Entity entity;

  public SearchCondition() {}

  public SearchCondition(Class<Entity> entityClass) {
    try {
      entity = entityClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      log.error(this, e);
    }
  }
}

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

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;

/**
 * 搜索结果
 */
@Getter
@Setter
public class Result<E> {

  protected List<E> list = Lists.emptyList();
  protected int totalCount;
  private Map<String, Object> params = Maps.newHashMap();
  private Object data;
  private String msg;
  private boolean status;

  private Result() {
    super();
    setStatus(true);
  }

  public static Result makeSuccessResult(String msg) {
    return makeResult(msg, true);
  }

  public static Result makeSuccessResult() {
    return makeResult("", true);
  }

  public static Result makeErrorResult(String msg) {
    return makeResult(msg, false);
  }

  public static Result makeErrorResult() {
    return makeResult("", false);
  }

  public static Result makeResult(String msg, boolean status) {
    Result result = new Result();
    result.setMsg(msg);
    result.setStatus(status);
    return result;
  }

  public static Result makeStatusResult(boolean status) {
    return makeResult("", status);
  }

  public static Result makeSuccessResult(Object data) {
    return makeSuccessResult("", data);
  }

  public static Result makeSuccessResult(String msg, Object data) {
    Result result = new Result();
    result.setMsg(msg);
    result.setData(data);
    return result;
  }

  public static <E> Result makeResult(String msg, List<E> list, int totalCount) {
    Result result = new Result();
    result.setMsg(msg);
    result.setList(list);
    result.setTotalCount(totalCount);
    return result;
  }

  public static <E> Result makeSuccessResult(List<E> list, int totalCount) {
    return makeResult("", list, totalCount);
  }

  public static <E> Result makeSuccessResult(List<E> list) {
    return makeResult("", list, 0);
  }
}

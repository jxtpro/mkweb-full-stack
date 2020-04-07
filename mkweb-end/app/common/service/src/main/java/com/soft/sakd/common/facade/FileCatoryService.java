package com.soft.sakd.common.facade;

import com.soft.sakd.common.dto.FileCatoryDto;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 10:26
 */
public interface FileCatoryService {

  List<FileCatoryDto> queryList();
}

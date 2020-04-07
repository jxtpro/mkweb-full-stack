package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.UserArticle;
import java.util.List;

public interface UserArticleMapper {
    int insert(UserArticle record);

    List<UserArticle> selectAll();
}
package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.Article;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);
    // 分页查找所有文章
    List<Article> queryAll(@Param("pageSize") int pageSize, @Param("page") int page);
    // 统计所有文章总数
    Integer countArticle();

    Integer updateArticleLike(Article article);

    Integer updateArticleBrowse(Article article);
}
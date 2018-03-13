package org.throwable.blog.dao

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.throwable.blog.model.entity.ArticleContent

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/11 22:37
 * @description
 */
@Mapper
interface ArticleContentDao {

    fun fetchContentByArticleId(@Param("id")id : Long) :ArticleContent
}
package org.throwable.blog.dao

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.throwable.blog.model.entity.Article

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/11 22:38
 * @description
 */
@Mapper
interface ArticleDao {

    fun fetchById(@Param("id") id: Long): Article

    fun queryAllArticles(): List<Article>

    fun queryArticlesByChildCategoryId(@Param("childCategoryId") categoryId: Long): List<Article>

    fun queryArticlesByParentCategoryId(@Param("parentCategoryId") parentCategoryId: Long): List<Article>

    fun increaseViewById(@Param("id") id: Long?): Int
}
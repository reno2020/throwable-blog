package org.throwable.blog.model.dto

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/12 0:49
 * @description
 */
data class ArticleListDTO(
        var createTime: String? = null,
        var title: String? = null,
        var description: String? = null,
        var id: Long? = null,
        var views: Int? = null,
        var parentCategoryId: Long? = null,
        var parentCategoryName: String? = null,
        var childCategoryId: Long? = null,
        var childCategoryName: String? = null,
        var articleType: Int? = null,
        var author: String? = null
)
package org.throwable.blog.model.entity

import java.time.LocalDateTime

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/11 22:34
 * @description
 */
data class Article(
        var id: Long? = null,
        var title: String? = null,
        var description: String? = null,
        var parentCategoryId: Long? = null,
        var parentCategoryName: String? = null,
        var childCategoryId: Long? = null,
        var childCategoryName: String? = null,
        var createTime: LocalDateTime? = null,
        var updateTime: LocalDateTime? = null,
        var views: Int? = null,
        var replies: Int? = null,
        var articleType: Int? = null,
        var author: String? = null,
        var articleUrl: String? = null
)
package org.throwable.blog.model.entity

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/11 22:34
 * @description
 */
data class CategoryCount(
        var id: Long? = null,
        var categoryId: Long? = null,
        var pid: Long? = null,
        var categoryName: String? = null,
        var totalCount: Int? = null
)
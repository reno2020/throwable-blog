package org.throwable.blog.model.entity

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/11 22:32
 * @description
 */
data class Category(
        var id: Long? = null,
        var categoryName: String? = null,
        var pid: Long? = null,
        var sort: Int? = null
)
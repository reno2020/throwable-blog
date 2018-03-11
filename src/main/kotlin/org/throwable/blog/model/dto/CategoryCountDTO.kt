package org.throwable.blog.model.dto

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/12 0:47
 * @description
 */
data class CategoryCountDTO(
        var categoryId: Long? = null,
        var categoryName: String? = null,
        var totalCount: Int? = null
)
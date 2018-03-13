package org.throwable.blog.model.entity

import java.time.LocalDateTime

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/12 23:22
 * @description
 */
data class Link(
        var id: Long? = null,
        var title: String? = null,
        var url: String? = null,
        var sort: Int? = null,
        var createTime: LocalDateTime? = null,
        var updateTime: LocalDateTime? = null,
        var isDelete: Int? = null
)
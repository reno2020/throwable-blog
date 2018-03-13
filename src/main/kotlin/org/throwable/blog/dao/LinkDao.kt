package org.throwable.blog.dao

import org.apache.ibatis.annotations.Mapper
import org.throwable.blog.model.entity.Link

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/12 23:26
 * @description
 */
@Mapper
interface LinkDao {

    fun getAllLinks(): List<Link>
}
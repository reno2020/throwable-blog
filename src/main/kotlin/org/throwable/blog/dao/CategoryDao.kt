package org.throwable.blog.dao

import org.apache.ibatis.annotations.Mapper
import org.throwable.blog.model.entity.Category

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/11 22:38
 * @description
 */
@Mapper
interface CategoryDao {

    fun queryAllParentCategories(): List<Category>

    fun queryByPid(pid: Long): List<Category>

    fun queryById(id: Long):Category
}
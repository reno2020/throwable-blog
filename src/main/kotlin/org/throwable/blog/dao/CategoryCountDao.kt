package org.throwable.blog.dao

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.throwable.blog.model.entity.CategoryCount

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/11 22:39
 * @description
 */
@Mapper
interface CategoryCountDao {

    fun queryAllCategoryCountByPid(@Param("pid") pid: Long): List<CategoryCount>
}
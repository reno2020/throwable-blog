package org.throwable.blog.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.throwable.blog.dao.ArticleDao
import org.throwable.blog.model.dto.ArticleListDTO

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/12 0:52
 * @description
 */
@Service
class BlogService {

    @Autowired
    private lateinit var articleDao: ArticleDao

    fun processArticles(categoryId: Long, pageNumber: Long, pageSize: Long): ArticleListDTO {
        val articleListDTO = ArticleListDTO()
        return articleListDTO
    }

}
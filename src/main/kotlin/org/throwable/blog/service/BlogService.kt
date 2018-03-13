package org.throwable.blog.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.throwable.blog.dao.*
import org.throwable.blog.model.dto.ArticleDTO
import org.throwable.blog.model.dto.ArticleListDTO
import org.throwable.blog.model.entity.*
import org.throwable.blog.utils.LocalDateTimeUtils

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

    @Autowired
    private lateinit var linkDao: LinkDao

    @Autowired
    private lateinit var categoryDao: CategoryDao

    @Autowired
    private lateinit var categoryCountDao: CategoryCountDao

    @Autowired
    private lateinit var articleContentDao: ArticleContentDao

    fun extractIndexCategory(catetories: List<Category>): Category {
        return catetories.first { it.sort == 1 }
    }

    fun queryAllParentCategories(): List<Category> {
        return categoryDao.queryAllParentCategories().sortedBy { it.sort }
    }

    fun queryAllLinks(): List<Link> {
        val links = linkDao.getAllLinks()
        return links.sortedBy { it.sort }
    }

    fun queryAllArticles(): List<ArticleListDTO> {
        val articles = articleDao.queryAllArticles().sortedBy { it.createTime }
        val articlesDTO = ArrayList<ArticleListDTO>(articles.size)
        articles.forEach {
            articlesDTO.add(ArticleListDTO(LocalDateTimeUtils.format(it.createTime), it.title, it.description, it.id))
        }
        return articlesDTO
    }

    fun queryArticlesByCategoryId(categoryId: Long): List<ArticleListDTO> {
        val category = categoryDao.queryById(categoryId)
        val articles =
                if (null != category && category.pid == 0L) {
                    articleDao.queryArticlesByCategoryParentId(categoryId).sortedBy { it.createTime }
                } else {
                    articleDao.queryArticlesByCategoryId(categoryId).sortedBy { it.createTime }
                }
        val articlesDTO = ArrayList<ArticleListDTO>(articles.size)
        articles.forEach {
            articlesDTO.add(ArticleListDTO(LocalDateTimeUtils.format(it.createTime), it.title, it.description, it.id))
        }
        return articlesDTO
    }

    fun queryAllCategoryCountByPid(pid: Long): List<CategoryCount> {
        return categoryCountDao.queryAllCategoryCountByPid(pid)
    }

    fun queryArticleById(id: Long): ArticleDTO {
        val article = articleDao.fetchById(id)
        return ArticleDTO(
                id = article.id,
                categoryId = article.categoryId,
                categoryName = article.categoryName,
                createTime = LocalDateTimeUtils.format(article.createTime),
                updateTime = LocalDateTimeUtils.format(article.updateTime),
                description = article.description,
                replies = article.replies,
                title = article.title,
                views = article.views
        )
    }

    fun queryArticleContentByArticleId(id: Long): ArticleContent {
        return articleContentDao.fetchContentByArticleId(id)
    }

    fun fetchParentCategory(categoryId: Long): Category? {
        return categoryDao.queryById(categoryId)
    }
}
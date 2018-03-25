package org.throwable.blog.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.throwable.blog.dao.*
import org.throwable.blog.model.dto.ArticleDTO
import org.throwable.blog.model.dto.ArticleListDTO
import org.throwable.blog.model.entity.ArticleContent
import org.throwable.blog.model.entity.Category
import org.throwable.blog.model.entity.CategoryCount
import org.throwable.blog.model.entity.Link
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

    fun extractIndexCategory(categories: List<Category>): Category {
        return categories.first { it.sort == 1 }
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
            articlesDTO.add(ArticleListDTO(
                    LocalDateTimeUtils.format(it.createTime),
                    it.title,
                    it.description,
                    it.id,
                    it.views,
                    it.parentCategoryId,
                    it.parentCategoryName,
                    it.childCategoryId,
                    it.childCategoryName,
                    it.articleType,
                    it.author,
                    it.articleUrl
            ))
        }
        return articlesDTO
    }

    fun queryArticlesByParentCategoryId(parentCategoryId: Long): List<ArticleListDTO> {
        val articles = articleDao.queryArticlesByParentCategoryId(parentCategoryId)
        val articlesDTO = ArrayList<ArticleListDTO>(articles.size)
        articles.forEach {
            articlesDTO.add(ArticleListDTO(
                    LocalDateTimeUtils.format(it.createTime),
                    it.title,
                    it.description,
                    it.id,
                    it.views,
                    it.parentCategoryId,
                    it.parentCategoryName,
                    it.childCategoryId,
                    it.childCategoryName,
                    it.articleType,
                    it.author,
                    it.articleUrl
            ))
        }
        return articlesDTO
    }

    fun queryArticlesByChildCategoryId(childCategoryId: Long): List<ArticleListDTO> {
        val articles = articleDao.queryArticlesByChildCategoryId(childCategoryId)
        val articlesDTO = ArrayList<ArticleListDTO>(articles.size)
        articles.forEach {
            articlesDTO.add(ArticleListDTO(
                    LocalDateTimeUtils.format(it.createTime),
                    it.title,
                    it.description,
                    it.id,
                    it.views,
                    it.parentCategoryId,
                    it.parentCategoryName,
                    it.childCategoryId,
                    it.childCategoryName,
                    it.articleType,
                    it.author,
                    it.articleUrl
            ))
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
                parentCategoryId = article.parentCategoryId,
                parentCategoryName = article.parentCategoryName,
                childCategoryId = article.childCategoryId,
                childCategoryName = article.childCategoryName,
                createTime = LocalDateTimeUtils.format(article.createTime),
                updateTime = LocalDateTimeUtils.format(article.updateTime),
                description = article.description,
                replies = article.replies,
                title = article.title,
                views = article.views,
                articleType = article.articleType,
                author = article.author,
                articleUrl = article.articleUrl
        )
    }

    fun queryArticleContentByArticleId(id: Long): ArticleContent {
        return articleContentDao.fetchContentByArticleId(id)
    }

    fun fetchCategoryById(categoryId: Long): Category {
        return categoryDao.queryById(categoryId)
    }
}
package org.throwable.blog.event.listnener

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.throwable.blog.dao.ArticleDao
import org.throwable.blog.event.ArticleViewEvent

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/3/13 15:55
 */
@Component
class ArticleViewEventListener {

    @Autowired
    private lateinit var articleDao: ArticleDao

    @EventListener
    fun listen(articleViewEvent: ArticleViewEvent) :Int{
       return articleDao.increaseViewById(articleViewEvent.articleId)
    }
}
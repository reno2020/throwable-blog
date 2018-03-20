package org.throwable.blog.listener

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.throwable.blog.dao.ArticleDao
import org.throwable.blog.event.ArticleViewEvent

/**
 * @author throwable
 * @version v1.0
 * @description 用于增加每篇文章的阅读数，限制单个消费者，基于单线程不会产生并发问题
 * @since 2018/3/19 11:32
 */
@Component
class ViewIncrementListener : MessageListener {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ViewIncrementListener::class.java)
    }

    @Autowired
    private lateinit var articleDao: ArticleDao

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    override fun onMessage(message: Message) {
        try {
            val event = objectMapper.readValue(String(message.body), ArticleViewEvent::class.java)
            articleDao.increaseViewById(event.articleId)
        } catch (e: Exception) {
            LOGGER.error(e.message, e)
        }
    }
}
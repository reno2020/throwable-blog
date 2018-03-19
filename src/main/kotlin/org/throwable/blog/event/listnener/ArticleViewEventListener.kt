package org.throwable.blog.event.listnener

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.throwable.blog.common.BlogConstant
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
    private lateinit var rabbitTemplate: RabbitTemplate

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @EventListener
    fun listen(articleViewEvent: ArticleViewEvent) {
        rabbitTemplate.send(BlogConstant.ARTICLE_VIEW_INCREMENT_QUEUE,
                BlogConstant.ARTICLE_VIEW_INCREMENT_QUEUE,
                MessageBuilder.withBody(objectMapper.writeValueAsString(articleViewEvent).toByteArray()).build())
    }
}
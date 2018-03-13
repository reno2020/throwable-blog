package org.throwable.blog.event

import org.springframework.context.ApplicationEvent

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/3/13 15:54
 */
class ArticleViewEvent(var articleId: Long? = null) : ApplicationEvent("view") {


}
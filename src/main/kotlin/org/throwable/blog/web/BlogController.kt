package org.throwable.blog.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.throwable.blog.model.dto.ArticleListDTO
import org.throwable.blog.service.BlogService

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/12 0:51
 * @description
 */
@RestController
class BlogController {

    @Autowired
    private lateinit var blogService: BlogService

    @GetMapping(value = ["/articles"])
    fun getArticles(@RequestParam(name = "categoryId") categoryId: Long,
                    @RequestParam(name = "pageNumber", defaultValue = "1") pageNumber: Long,
                    @RequestParam(name = "pageSize", defaultValue = "20") pageSize: Long): ArticleListDTO {
        return blogService.processArticles(categoryId, pageNumber, pageSize)
    }
}
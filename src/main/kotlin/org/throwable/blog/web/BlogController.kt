package org.throwable.blog.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import org.throwable.blog.event.ArticleViewEvent
import org.throwable.blog.service.BlogService

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/12 0:51
 * @description
 */
@Controller
class BlogController {

    @Autowired
    private lateinit var blogService: BlogService

    @Autowired
    private lateinit var simpleApplicationEventMulticaster: SimpleApplicationEventMulticaster

    @GetMapping(value = ["/", "/index"])
    fun index(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        modelAndView.addObject("links", blogService.queryAllLinks())
        val categories = blogService.queryAllParentCategories()
        modelAndView.addObject("categories", categories.filter { it.sort != 1 })
        modelAndView.addObject("home", blogService.extractIndexCategory(categories))
        modelAndView.addObject("articles", blogService.queryAllArticles())
        return modelAndView
    }

    @GetMapping(value = ["/category/{id}"])
    fun category(@PathVariable(name = "id", required = true) id: Long): ModelAndView {
        val modelAndView = ModelAndView()
        val categories = blogService.queryAllParentCategories()
        modelAndView.viewName = "category"
        modelAndView.addObject("categoryCounts", blogService.queryAllCategoryCountByPid(id))
        modelAndView.addObject("categories", categories.filter { it.id != 1L })
        modelAndView.addObject("category", categories.last { it.id == id })
        modelAndView.addObject("id", id)
        modelAndView.addObject("articles", blogService.queryArticlesByCategoryId(id))
        return modelAndView
    }

    @GetMapping(value = ["/content/{id}"])
    fun content(@PathVariable(name = "id", required = true) id: Long): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "content"
        val article = blogService.queryArticleById(id)
        val categoryId = article.parentCategoryId ?: 0
        val categories = blogService.queryAllParentCategories()
        val parentCategory = blogService.fetchParentCategory(categoryId)
        modelAndView.addObject("article", article)
        modelAndView.addObject("id", id)
        modelAndView.addObject("categoryId", categoryId)
        modelAndView.addObject("parentCategoryId", parentCategory?.id ?: 0)
        modelAndView.addObject("categories", categories.filter { it.id != 1L })
        //异步发送增加浏览量的消息
        simpleApplicationEventMulticaster.multicastEvent(ArticleViewEvent(id))
        return modelAndView
    }

    @GetMapping(value = ["/article/content/{id}"])
    @ResponseBody
    fun articleContent(@PathVariable(name = "id", required = true) id: Long): String? {
        return blogService.queryArticleContentByArticleId(id).content
    }
}
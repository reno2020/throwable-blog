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

    @GetMapping(value = ["/parent/category/{id}"])
    fun parentCategory(@PathVariable(name = "id", required = true) id: Long): ModelAndView {
        val modelAndView = ModelAndView()
        val categories = blogService.queryAllParentCategories()
        modelAndView.viewName = "parent_category"
        modelAndView.addObject("categoryCounts", blogService.queryAllCategoryCountByPid(id))
        modelAndView.addObject("categories", categories.filter { it.id != 1L })
        modelAndView.addObject("category", categories.last { it.id == id })
        modelAndView.addObject("id", id)
        modelAndView.addObject("articles", blogService.queryArticlesByParentCategoryId(id))
        return modelAndView
    }

    @GetMapping(value = ["/child/category/{id}"])
    fun childCategory(@PathVariable(name = "id", required = true) id: Long): ModelAndView {
        val modelAndView = ModelAndView()
        val categories = blogService.queryAllParentCategories()
        val currentSubCategory = blogService.fetchCategoryById(id)
        modelAndView.viewName = "child_category"
        modelAndView.addObject("categories", categories.filter { it.id != 1L })
        modelAndView.addObject("category", currentSubCategory)
        modelAndView.addObject("parentCategory", categories.first { it.id == currentSubCategory.pid })
        modelAndView.addObject("id", id)
        modelAndView.addObject("articles", blogService.queryArticlesByChildCategoryId(id))
        return modelAndView
    }

    @GetMapping(value = ["/content/{id}"])
    fun content(@PathVariable(name = "id", required = true) id: Long): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "content"
        val article = blogService.queryArticleById(id)
        val categories = blogService.queryAllParentCategories()
        modelAndView.addObject("article", article)
        modelAndView.addObject("id", id)
        modelAndView.addObject("categories", categories.filter { it.id != 1L })
        //首页的类型id必须为1L
        modelAndView.addObject("homeCategoryId", 1L)
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
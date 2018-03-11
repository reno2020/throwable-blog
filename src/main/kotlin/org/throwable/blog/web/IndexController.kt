package org.throwable.blog.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author throwable
 * @version v1.0
 * @since 2018/3/10 9:08
 * @description
 */
@Controller
class IndexController {

    @GetMapping(value = ["/", "/index"])
    fun index(): String {
       return "index"
    }

    @GetMapping(value = ["/content"])
    fun content(): String {
        return "content"
    }
}
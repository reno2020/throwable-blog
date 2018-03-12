package org.throwable.blog.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDateTime

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

    @GetMapping(value = ["/data"])
    @ResponseBody
    fun data(): Map<String, Any> {
        val map = HashMap<String, Any>()
        map["now"] = LocalDateTime.now()
        return map
    }
}
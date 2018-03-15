package org.throwable.blog.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/3/12 17:28
 */
@ConfigurationProperties(value = DruidProperties.PREFIX)
data class DruidProperties(
        var url: String? = null,
        var username: String? = null,
        var password: String? = null,
        var driverClassName: String? = null,
        var porperties: Map<String, String>? = null
) {
    companion object {
        const val PREFIX = "druid"
    }

    fun convertProperties(): Properties {
        val p = Properties()
        porperties?.entries?.forEach {
            p[it.key] = it.value
        }
        return p
    }
}
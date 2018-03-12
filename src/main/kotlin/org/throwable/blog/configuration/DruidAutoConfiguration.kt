package org.throwable.blog.configuration

import com.alibaba.druid.pool.DruidDataSource
import com.alibaba.druid.pool.DruidDataSourceFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/3/12 17:33
 */
@Configuration
@EnableConfigurationProperties(value = [DruidProperties::class])
class DruidAutoConfiguration(var druidProperties: DruidProperties) {

    @Bean
    fun druidDataSource(): DruidDataSource {
        val druidDataSource = if (null != druidProperties.porperties) {
            DruidDataSourceFactory.createDataSource(druidProperties.porperties) as DruidDataSource
        } else {
            DruidDataSource()
        }
        druidDataSource.url = druidProperties.url
        druidDataSource.username = druidProperties.username
        druidDataSource.password = druidProperties.password
        druidDataSource.driverClassName = druidProperties.driverClassName
        return druidDataSource
    }
}
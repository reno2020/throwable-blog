package org.throwable.blog.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicInteger
import javax.annotation.PostConstruct

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/3/13 15:35
 */
@Configuration
class BlogAutoConfiguration(var simpleApplicationEventMulticaster: SimpleApplicationEventMulticaster) {

    companion object {
        const val THREAD_POOL_BEAN_NAME = "commonThreadPool"
        const val THREAD_PREFIX = "common-thread"
    }

    @Bean(name = [THREAD_POOL_BEAN_NAME])
    fun threadPoolTaskExecutor(): ThreadPoolTaskExecutor {
        val threadPool = ThreadPoolTaskExecutor()
        threadPool.corePoolSize = 10
        threadPool.maxPoolSize = 10
        threadPool.setQueueCapacity(50)
        threadPool.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        threadPool.setThreadFactory(NameThreadFactory())
        return threadPool
    }

    class NameThreadFactory : ThreadFactory {

        private val counter = AtomicInteger()

        override fun newThread(r: Runnable?): Thread {
            val thread = Thread(r)
            thread.isDaemon = true
            thread.name = "$THREAD_PREFIX-" + counter.incrementAndGet()
            return thread
        }
    }

    @PostConstruct
    fun postConstruct() {
        simpleApplicationEventMulticaster.setTaskExecutor(threadPoolTaskExecutor())
    }
}
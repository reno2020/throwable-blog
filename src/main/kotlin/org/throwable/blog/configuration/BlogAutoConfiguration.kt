package org.throwable.blog.configuration

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.throwable.blog.common.BlogConstant
import org.throwable.blog.listener.ViewIncrementListener
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

        override fun newThread(runnable: Runnable?): Thread {
            val thread = Thread(runnable)
            thread.isDaemon = true
            thread.name = "$THREAD_PREFIX-" + counter.incrementAndGet()
            return thread
        }
    }

    @PostConstruct
    fun postConstruct() {
        simpleApplicationEventMulticaster.setTaskExecutor(threadPoolTaskExecutor())
    }

    @Bean
    fun articleViewEventListenerContainer(rabbitConnectionFactory: CachingConnectionFactory,
                                          amqpAdmin: AmqpAdmin,
                                          viewIncrementListener: ViewIncrementListener): SimpleMessageListenerContainer {
        val queue = Queue(BlogConstant.ARTICLE_VIEW_INCREMENT_QUEUE)
        amqpAdmin.declareQueue(queue)
        val exchange = DirectExchange(BlogConstant.ARTICLE_VIEW_INCREMENT_QUEUE)
        amqpAdmin.declareExchange(exchange)
        val binding = BindingBuilder.bind(queue).to(exchange).with(BlogConstant.ARTICLE_VIEW_INCREMENT_QUEUE)
        amqpAdmin.declareBinding(binding)
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = rabbitConnectionFactory
        container.setQueueNames(BlogConstant.ARTICLE_VIEW_INCREMENT_QUEUE)
        //这里初始化消费者和最大消费者都设置为1可以防止并发
        container.setConcurrentConsumers(1)
        container.setMaxConcurrentConsumers(1)
        container.acknowledgeMode = AcknowledgeMode.NONE
        container.messageListener = viewIncrementListener
        return container
    }
}
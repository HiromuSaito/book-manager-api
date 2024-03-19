package com.book.manager.bookmanager.presentation.config

import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@EnableRedisHttpSession
class HttpSessionConfig {
    @Bean
    fun connectionFactory(): JedisConnectionFactory {
//        val redisStandaloneConfiguration = RedisStandaloneConfiguration().also {
//            it.hostName = "kotlin-redis"
//            it.port = 16379
//        }
//        return JedisConnectionFactory(redisStandaloneConfiguration)
        return JedisConnectionFactory()
    }
}
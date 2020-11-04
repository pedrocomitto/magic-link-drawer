package com.pedrocomitto.drawer.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
class RedisConfiguration(
    private val clusterProperties: ClusterConfigurationProperties
) {

    @Bean
    fun redisConnectionFactory() =
        JedisConnectionFactory(
            RedisClusterConfiguration(clusterProperties.nodes)
                .also { it.password = RedisPassword.of(clusterProperties.password)}
        )

    @Bean
    fun clusterRedisTemplate(redisConnectionFactory: JedisConnectionFactory) =
        StringRedisTemplate(redisConnectionFactory)
}

@ConstructorBinding
@ConfigurationProperties(value = "spring.redis.cluster")
data class ClusterConfigurationProperties(
    val nodes: List<String>,
    val password: String
)
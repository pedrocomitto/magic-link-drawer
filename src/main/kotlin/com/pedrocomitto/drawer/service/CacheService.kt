package com.pedrocomitto.drawer.service

import java.time.Duration
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class CacheService(
    private val redisTemplate: StringRedisTemplate
) {

    operator fun get(key: String) =
        redisTemplate.opsForValue().get(key)

    fun set(key: String, value: String, ttl: Duration) =
        redisTemplate.opsForValue().set(key, value, ttl)

}
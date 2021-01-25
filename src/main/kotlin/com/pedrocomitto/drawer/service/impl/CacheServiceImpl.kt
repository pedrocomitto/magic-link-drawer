package com.pedrocomitto.drawer.service.impl

import com.pedrocomitto.drawer.service.CacheService
import java.time.Duration
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class CacheServiceImpl(
    private val redisTemplate: StringRedisTemplate
) : CacheService {

    override operator fun get(key: String) =
        redisTemplate.opsForValue().get(key)

    override fun set(key: String, value: String, ttl: Duration) =
        redisTemplate.opsForValue().set(key, value, ttl)

}
package com.pedrocomitto.drawer.service

import java.time.Duration

interface CacheService {

    operator fun get(key: String): String?

    fun set(key: String, value: String, ttl: Duration)
}

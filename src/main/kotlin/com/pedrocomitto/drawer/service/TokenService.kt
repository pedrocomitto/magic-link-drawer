package com.pedrocomitto.drawer.service

import io.jsonwebtoken.Claims

interface TokenService {

    fun createToken(claims: HashMap<String, Any>): String

    fun parseToken(token: String): Claims

}

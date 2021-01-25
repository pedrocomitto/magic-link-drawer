package com.pedrocomitto.drawer.service.impl


import com.pedrocomitto.drawer.exception.ExpiredTokenException
import com.pedrocomitto.drawer.service.TokenService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.stereotype.Service

@Service
class TokenServiceImpl : TokenService {

    val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    override fun createToken(claims: HashMap<String, Any>): String =
            Jwts.builder()
                    .setClaims(claims)
                    .signWith(key)
                    .setExpiration(Date(System.currentTimeMillis() + 300_000))
                    .compact()

    override fun parseToken(token: String): Claims {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).body
        } catch (e: ExpiredJwtException) {
            throw ExpiredTokenException()
        }
    }

}
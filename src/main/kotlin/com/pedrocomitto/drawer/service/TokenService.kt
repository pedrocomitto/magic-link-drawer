package com.pedrocomitto.drawer.service


import com.pedrocomitto.drawer.exception.ExpiredTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.stereotype.Service

@Service
class TokenService {

    val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun createToken(claims: HashMap<String, Any>) =
            Jwts.builder()
                    .setClaims(claims)
                    .signWith(key)
                    .setExpiration(Date(System.currentTimeMillis() + 300_000))
                    .compact()

    fun parseToken(token: String): Claims {
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
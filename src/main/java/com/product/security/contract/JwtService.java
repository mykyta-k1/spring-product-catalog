package com.product.security.contract;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.UUID;

public interface JwtService {

    String generateToken(UUID userId);

    UUID pullsOutUserIdFromToken(String token) throws ExpiredJwtException, SignatureException, MalformedJwtException;
}

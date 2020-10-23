package com.mortgage.loans.api.utils;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
	
	public static final String SECRET = "testing";
	public static final Long EXPIRE_TIME = 1800000L;
	
	public String createToken(String email) {
		Algorithm algorithm = Algorithm.HMAC256(SECRET);
		
		long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    long expMillis = nowMillis + EXPIRE_TIME;
        Date exp = new Date(expMillis);
        
		String token = JWT.create().withSubject(email).withIssuedAt(now).withExpiresAt(exp).sign(algorithm);
		return token;
	}
	
	public boolean validateToken(String token, String email) {
		boolean flag = false;
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier varifier = JWT.require(algorithm).build();
			DecodedJWT jwt = varifier.verify(token);
			flag = jwt.getSubject().equals(email);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}

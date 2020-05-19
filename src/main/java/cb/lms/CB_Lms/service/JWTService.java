package cb.lms.CB_Lms.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {


	
	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(String username, Map<String,Object> claims) {
		
		Instant expriryTime = Instant.now().plus(Duration.ofMinutes(20));

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(expriryTime))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	
	public String refreshToken(String username,Map<String,Object> claims) {
		
		
		Instant expriryTime = Instant.now().plus(Duration.ofHours(1));

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(expriryTime))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public Integer getUserIdFromClaim(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return (Integer)claims.get("userId");
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Boolean validateToken(String token) {
//		final String username = getUsernameFromToken(token);
		return (!isTokenExpired(token));
	}
	
//	public boolean validateTokenHMAC256(String token, String secret) throws UnsupportedEncodingException, JWTVerificationException
//    {       
//        Algorithm algorithm = Algorithm.HMAC256(secret);
//
//
//        JWTVerifier verifier = JWT.require(algorithm) 
//                .build(); //Reusable verifier instance
//            DecodedJWT jwt = verifier.verify(token);
//
//            Claim usernameClaim = jwt.getClaim("username");
//            String username = usernameClaim.asString();
//            System.out.println(username);
//
//
//        return true;
//    }
}

package pl.pwr.enrollment.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenValidationService {

	private final Algorithm cipherAlgorithm;

	public TokenValidationService(@Value("${auth.secret:P26mwUeQ664l}") String secret) {
		this.cipherAlgorithm = Algorithm.HMAC512(secret.getBytes());
	}

	public Token validate(String token) {
		JWT.require(cipherAlgorithm)
				.build()
				.verify(token);

		return new Token(token);
	}

}

package pl.pwr.enrollment.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.List;

public class Token {

	private static final String CLAIM_AUTHORITIES = "authorities";
	private static final String CLAIM_USER_ID = "userId";

	private final String value;
	private DecodedJWT decodedToken;

	public Token(String value) {
		this.value = value;
	}

	public List<String> getAuthorities() {
		return getDecoded().getClaim(CLAIM_AUTHORITIES).asList(String.class);
	}

	public Long getUserId() {
		return getDecoded().getClaim(CLAIM_USER_ID).asLong();
	}

	public String getSubject() {
		return getDecoded().getSubject();
	}

	public String getValue() {
		return value;
	}

	private DecodedJWT getDecoded() {
		if (decodedToken == null) {
			decodedToken = JWT.decode(value);
		}

		return decodedToken;
	}
}

package pl.pwr.enrollment.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final String TOKEN_PREFIX = "Bearer ";
	private static final String HEADER_STRING = "Authorization";

	private final TokenValidationService tokenValidationService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, TokenValidationService tokenValidationService) {
		super(authenticationManager);
		this.tokenValidationService = tokenValidationService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader(HEADER_STRING);

		if (header != null && header.startsWith(TOKEN_PREFIX)) {
			tryAuthenticate(request);
		}

		chain.doFilter(request, response);
	}

	private void tryAuthenticate(HttpServletRequest request) {
		String tokenHeaderValue = request.getHeader(HEADER_STRING);
		if (tokenHeaderValue == null) {
			return;
		}

		validateToken(tokenHeaderValue).ifPresent(token -> {
			SecurityContextHolder.getContext().setAuthentication(authFromToken(token));
		});
	}

	private Authentication authFromToken(Token token) {
		List<SimpleGrantedAuthority> authorities = token.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
				.collect(toList());

		return new UsernamePasswordAuthenticationToken(
				new TokenUserDetails(token.getUserId(), token.getSubject(), authorities),
				null,
				authorities
		);
	}

	private Optional<Token> validateToken(String tokenHeaderValue) {
		try {
			Token token = tokenValidationService.validate(tokenHeaderValue.replace(TOKEN_PREFIX, ""));
			return Optional.of(token);
		} catch (JWTVerificationException e) {
			return Optional.empty();
		}
	}
}

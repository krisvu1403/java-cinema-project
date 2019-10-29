package com.myproject.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final String SECRET = "KHOA";
	private final String TOKEN_PREFIX = "Bearer ";
	private final String HEADER_STRING = "Authorization";

	private UserDetailsService _userdetDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			UserDetailsService userdetDetailsService) {
		super(authenticationManager);
		this._userdetDetailsService = userdetDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HEADER_STRING);
		
		// Header with out token or wrong token format.
		if (header == "" && !header.startsWith(TOKEN_PREFIX)) {
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("Login failed");
			response.getWriter().close();
			return;
		}

		UsernamePasswordAuthenticationToken authenticationToken = getauthenticationtoken(request);

		// Set thong tin user dang nhap vao toan he thong
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
		System.out.println("ENDDD doFilterInternal");
	}

	private UsernamePasswordAuthenticationToken getauthenticationtoken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		// Giai ma token
		String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
				.getSubject();
		
		if (username == "") {
			return null;
		}

		UserDetails userDetails = _userdetDetailsService.loadUserByUsername(username);
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}

package com.sijojosan.arya.core.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.sijojosan.arya.core.dal.entities.UserTokenEntity;
import com.sijojosan.arya.core.dal.repository.UserTokenRepository;
import com.sijojosan.arya.core.utilities.AryaCoreUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class AuthenticationFilter extends GenericFilterBean {

	@Autowired
	UserTokenRepository userTokenRepository;

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {

			if (authHeader == null) {
				throw new ServletException("Missing or invalid Authorization header.");
			}

			final String token = authHeader;

			try {
				final Claims claims = Jwts.parser().setSigningKey(AryaCoreUtility.getEnviromentContent(AryaCoreUtility.SECRET_KEY)).parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				String userName = claims.getSubject();

				UserTokenEntity userTokenEntity = userTokenRepository.findByUserName(userName);

				if (userTokenEntity == null || !userTokenEntity.getToken().equals(token)) {
					throw new ServletException("Invalid token.");
				}
			} catch (final SignatureException e) {
				throw new ServletException("Invalid token.");
			}

			chain.doFilter(req, res);
		}
	}
}

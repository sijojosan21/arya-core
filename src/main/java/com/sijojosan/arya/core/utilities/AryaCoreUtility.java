package com.sijojosan.arya.core.utilities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sijojosan.arya.core.dal.entities.EnviromentContentEntity;
import com.sijojosan.arya.core.dal.repository.EnviromentContentRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AryaCoreUtility {
	
	public static final String SECRET_KEY="secretKey";

	private static final Map<String, String> enviromentContent = new HashMap<>();

	@Autowired
	EnviromentContentRepository enviromentContentRepository;

	public static String getAuthenticationToken(String subject) {
		String authToken = Jwts.builder().setSubject(subject).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, getEnviromentContent(SECRET_KEY)).compact();
		return authToken;
	}

	@EventListener
	public void onApplicationReady(ApplicationReadyEvent ready) {
		List<EnviromentContentEntity> enviromentCont = (List<EnviromentContentEntity>) enviromentContentRepository.findAll();

		for (EnviromentContentEntity ev : enviromentCont) {
			addEnviromentContent(ev.getKey(), ev.getValue());
		}
	}

	private static void addEnviromentContent(String key, String value) {
		enviromentContent.put(key, value);
	}

	public static String getEnviromentContent(String key) {
		return enviromentContent.get(key);
	}

}

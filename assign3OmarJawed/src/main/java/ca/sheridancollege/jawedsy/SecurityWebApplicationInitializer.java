package ca.sheridancollege.jawedsy;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import ca.sheridancollege.jawedsy.security.SecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
		}

}

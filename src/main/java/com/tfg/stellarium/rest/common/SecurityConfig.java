package com.tfg.stellarium.rest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/** The jwt generator. */
	@Autowired
	private JwtGenerator jwtGenerator;

	private static final String CLIENT = "CLIENT"; 
	private static final String ADMIN = "ADMIN";
	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilter(new JwtFilter(authenticationManager(), jwtGenerator)).authorizeRequests()
				.antMatchers(HttpMethod.POST,"/users/signUp").permitAll()
				.antMatchers(HttpMethod.POST,"/users/login").permitAll()
				.antMatchers(HttpMethod.POST,"/users/loginFromServiceToken").permitAll()
				.antMatchers(HttpMethod.POST, "/users/*/changePassword").permitAll()
				.antMatchers(HttpMethod.POST, "/imagenes/guardar").permitAll()
				.antMatchers(HttpMethod.POST, "/astros/guardar").permitAll()
				.antMatchers(HttpMethod.POST, "/posts").permitAll()
				.antMatchers(HttpMethod.GET, "/posts/*").permitAll()
				.antMatchers(HttpMethod.GET, "/posts/guardar").permitAll()
				.antMatchers(HttpMethod.DELETE, "/posts/*").permitAll()
		;
		// @formatter:on

	}

	/**
	 * Cors configuration source.
	 *
	 * @return the cors configuration source
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration config = new CorsConfiguration();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);

		return source;

	}

}

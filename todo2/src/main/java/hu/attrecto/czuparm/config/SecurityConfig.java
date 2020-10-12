package hu.attrecto.czuparm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity httpSec) throws Exception {
 			httpSec
 				.authorizeRequests()
 					.antMatchers("/api/base/registration").permitAll()
 					.antMatchers("/api/base/reg").permitAll()
 					.anyRequest().authenticated()
 					.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.logoutSuccessUrl("/login?logout")
					.permitAll();
				
		}
}

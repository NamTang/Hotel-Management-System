package com.example.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	private String userQuery = "select email, password, active from users where email=? ";
	private String roleQuery = "select u.email, r.role from users u inner join role r on(u.role_id=r.role_id) where u.email=?";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
								.antMatchers("/login").permitAll()
								.antMatchers("/register").permitAll()
								.antMatchers("/book").hasAuthority("Customer")
								.antMatchers("/admin/room/**").hasAnyAuthority("Admin", "Clerk").anyRequest().authenticated()
								.antMatchers("/admin/user/**").hasAuthority("Admin").anyRequest().authenticated()
								.and().csrf().disable().formLogin()
								.loginPage("/login").failureUrl("/login?error=true")
								.defaultSuccessUrl("/")
								.usernameParameter("email")
								.passwordParameter("password")
								.and().logout()
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.logoutSuccessUrl("/").and().exceptionHandling()
								.accessDeniedPage("/access-denied");
	
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(userQuery).authoritiesByUsernameQuery(roleQuery)
				.dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
      
    }

}
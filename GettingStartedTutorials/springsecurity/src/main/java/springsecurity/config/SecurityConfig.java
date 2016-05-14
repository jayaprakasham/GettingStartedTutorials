package springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure (AuthenticationManagerBuilder auth) throws Exception{
		//auth.inMemoryAuthentication().withUser("user1").password("password").roles("USER");
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "USER");
		
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from  users where username= ?")
			.authoritiesByUsernameQuery("select username, role from user_roles where username = ?");
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		
		httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		
		httpSecurity.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/hello/**").access("hasRole('USER')")
			.antMatchers("/admin/**").access("hasRole('ADMIN')")
			.anyRequest().authenticated();
		
		httpSecurity.formLogin().loginPage("/login").permitAll();
		
		httpSecurity.logout().permitAll();
		
		httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
	}
}

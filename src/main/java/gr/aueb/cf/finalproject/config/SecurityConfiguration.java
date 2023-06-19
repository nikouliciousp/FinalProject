package gr.aueb.cf.finalproject.config;

import gr.aueb.cf.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Inject UserService
	@Autowired
	private UserService userService;

	// Define the password encoder bean
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Define the authentication provider bean
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); 											// Set the custom userDetailsService implementation
		auth.setPasswordEncoder(passwordEncoder()); 										// Set the password encoder for authentication
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(
						"/registration**", 										// Allow access to the registration page
						"/js/**",
						"/css/**",
						"/img/**"
				).permitAll() 																// Permit all users to access these resources without authentication
				.antMatchers("/index_admin").hasRole("ADMIN") 					// Allow access to index_admin only for users with ADMIN role
				.antMatchers("/index_user").hasRole("USER") 						// Allow access to index_user only for users with USER role
				.antMatchers("/add_user_admin").hasRole("ADMIN") 					// Allow access to add_user_admin only for users with ADMIN role
				.antMatchers("/edit_user_admin").hasRole("ADMIN") 				// Allow access to add_user_admin only for users with ADMIN role
				.antMatchers("/delete_user_admin/**").hasRole("ADMIN")
				.antMatchers("/drink_categories/**").hasRole("ADMIN")
				.antMatchers("/add_drink_category_admin/**").hasRole("ADMIN")
				.antMatchers("/edit_drink_category_admin/**").hasRole("ADMIN")
				.antMatchers("/add_drink_admin/**").hasRole("ADMIN")
				.antMatchers("/edit_user/**").hasRole("USER")
				.anyRequest().authenticated() 												// Require authentication for all other requests
				.and()
				.formLogin()
				.loginPage("/login") 														// Specify the login page URL
				.permitAll() 																// Allow all users to access the login page
				.and()
				.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 			// Specify the logout URL
				.logoutSuccessUrl("/login?logout") 											// Redirect to this URL after successful logout
				.permitAll(); 																// Allow all users to access the logout URL
	}
}

package book_wizards.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                // TODO add antMatchers here to configure access to specific API endpoints
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/create_account").permitAll()
                // all endpoints to protect go here
                .antMatchers(HttpMethod.GET, "/api/author", "/api/author/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/author").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/author/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/author/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/book", "/api/book/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/book").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/book/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/book/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/genre", "/api/genre/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/genre").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/genre/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/genre/*").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/meeting", "/api/meeting/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/meeting").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/meeting/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/meeting/*").hasAnyRole("ADMIN")
                // require authentication for any request...
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}

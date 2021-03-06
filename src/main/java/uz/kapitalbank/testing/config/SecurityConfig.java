package uz.kapitalbank.testing.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.kapitalbank.testing.security.JwtAuthEntryPoint;
import uz.kapitalbank.testing.security.JwtConfigurer;
import uz.kapitalbank.testing.security.JwtTokenFilter;
import uz.kapitalbank.testing.security.JwtTokenProvider;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    public SecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider,
                          JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/api/auth/forgotpassword","/api/auth/checkcode/*","/api/auth/editpassword").permitAll()
//                .antMatchers("/api/auth/register", "/api/auth/login", "/api/auth/getme", "/api/posts/all","/api/posts/{id}","/api/files/preview/*","/api/files/download/*").permitAll()
//                .antMatchers("/api/auth/user/**").hasAuthority("ROLE_USER")
//                .antMatchers("/api/admin/**", "/api/files/all").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/api/owner/**", "/api/admin/users").hasAuthority("ROLE_OWNER")
//                .antMatchers("/api/posts/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
//                .antMatchers("/api/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

        http.addFilterBefore(jwtAuthenticationEntryPoint(), UsernamePasswordAuthenticationFilter.class);
    }

    private Filter jwtAuthenticationEntryPoint() {
        return new JwtTokenFilter(jwtTokenProvider);
    }
}
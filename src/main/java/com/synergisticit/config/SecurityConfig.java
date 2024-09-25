package com.synergisticit.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class SecurityConfig {
    
    @Autowired BCryptPasswordEncoder bCrypt;
    @Autowired UserDetailsService userDetailsService;
    @Autowired AccessDeniedHandler adh;
    
    @Bean
    DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCrypt);
        return authProvider;
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // http.authorizeHttpRequests().anyRequest().permitAll();  // this allows to bypass all http security
        
        http.authorizeHttpRequests()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()  // permits access to specified url without login
        .requestMatchers(AntPathRequestMatcher.antMatcher("/home")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/flightSearch")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/flightSearch**")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/register")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/registerUser")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/jsp/**")).permitAll()  // allow all jsp files
        .requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()  // allow all css
        .requestMatchers(AntPathRequestMatcher.antMatcher("/images/**")).permitAll()  // allow all images
        .requestMatchers(AntPathRequestMatcher.antMatcher("/icons/**")).permitAll()  // allow all icon images
        .requestMatchers(AntPathRequestMatcher.antMatcher("/js/**")).permitAll()  // allow all js to run css
        .anyRequest().authenticated()  // any url other than above must be authenticated
        // .and()
        // .httpBasic()  // browser to request log in when accessing pages with authentication required
        .and()
        .formLogin()
        .loginPage("/login")  // url for custom log in page
        .defaultSuccessUrl("/home", true)  // url upon successful log in
        .and()
        .exceptionHandling()
        .accessDeniedHandler(adh)
        .and()
        .logout()
        .invalidateHttpSession(true)  // invalidate session upon log out
        .clearAuthentication(true)  // clear authentication upon log out
        .logoutRequestMatcher(AntPathRequestMatcher.antMatcher("/logout"))  // url for logout
        .logoutSuccessUrl("/login?logout").permitAll()  // url after log out
        .and()
        .userDetailsService(userDetailsService)
        .csrf().disable();
        
        return http.build();
    }
    
}

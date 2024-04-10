package com.iKeeper.homepage.global.config;

import com.iKeeper.homepage.domain.auth.service.UserSignInService;
import com.iKeeper.homepage.global.jwt.handler.JwtAuthenticationFilter;
import com.iKeeper.homepage.global.jwt.handler.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@RequiredArgsConstructor
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSignInService userSignInService;

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/**").permitAll()
                .antMatchers("/api/users/join", "/api/users/login").permitAll()
                .antMatchers("/api/users/test").hasRole("USER")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/join").permitAll()
                .antMatchers("/").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated(); // 페이지 및 권한은 추후 분배 예정

        http
                .formLogin()
                .defaultSuccessUrl("/success")
                .failureForwardUrl("/fail")
                .usernameParameter("student")
                .passwordParameter("password");

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/users/logout"))
                .invalidateHttpSession(true);
    }
}
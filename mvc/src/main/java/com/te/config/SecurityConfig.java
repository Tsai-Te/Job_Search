package com.te.config;

import com.te.extend.security.JwtAuthenticationFilter;
import com.te.extend.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //become configuration bean
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    private JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter();

    @Bean(name= BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


////    step 1
//    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("{noop}password").roles("REGISTERED_USER");
//    }
//
//    protected void configure (HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//    }

    //    step 2
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder=new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication().withUser("user")//用户写死了。但我们需要从数据库里拿用户数据。hardcode
//                .password("{noop}password").roles("REGISTERED_USER");
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterAt(new AnonymousAuthenticationFilter())
        http.addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .csrf().disable()
                .authorizeRequests().antMatchers("/api/user/login", "/api/users/login","/api/users/login/admin","/api/users/login/admins","/api/user/login/admins","/api/user/login/admin","/api/users/signUp", "/api/user/signUp").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/admin/**","/api/admins/**").hasAnyRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/api/**").hasAnyRole("REGISTERED_USER", "ADMIN")
                .and()

                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}

//,"/api/users/login/admin","/api/users/login/admins","/api/user/login/admins","/api/user/login/admin"

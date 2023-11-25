package com.bolsadeideas.springboot.app;

import com.bolsadeideas.springboot.app.app.handler.LoginSuccesHandler;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SpringSecurityConfig2 extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private LoginSuccesHandler succesHandler;
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/","/css/*","/js/","/images/*","/listar")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(succesHandler)
                .loginPage("/login")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");

    }
    public void configurerGlobal (AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(jpaUserDetailsService).passwordEncoder(passwordEncoder);
    }

}

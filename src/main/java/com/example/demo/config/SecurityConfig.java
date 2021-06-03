package com.example.demo.config;

import com.example.demo.filter.JwtFilter;
import com.example.demo.handler.LoginFailureHandler;
import com.example.demo.handler.LoginSucessHandler;
import com.example.demo.handler.MyAuthenticationEntryPoint;
import com.example.demo.service.impl.MyUserDetailService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 15:36
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    LoginSucessHandler loginSucessHandler;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略静态文件
        web.ignoring().antMatchers("/a/**","/*.html","/**/*.html","/css/**","/js/**","/font/**","/images/**","/plugins/**","/bundles/**","/video/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭crsf跨站请求伪造
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)//自定义异常处理器
                .and()
                .authorizeRequests()
                .antMatchers("/index","/loginn","/signup","/creatUser","/file/getIdCardData")//忽略请求路径，不需要认证
                .permitAll()
                .anyRequest()
                .authenticated()//除了上面忽略得，所有请求都要认证
        .and()
        .formLogin()
                .defaultSuccessUrl("/index/sb",true)//登录成功后跳转页面
        .and().logout()
                .logoutSuccessUrl("/home");
//        .successHandler(loginSucessHandler)
//        .failureHandler(loginFailureHandler);//登录失败处理器

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置查询用户实现类，并设置密码加密模式
        auth.userDetailsService(myUserDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

package com.abs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }


    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/toLoginPage").permitAll()//访问该目录不需要任何权限
                .antMatchers("/index").permitAll()//访问该目录不需要任何权限
                .antMatchers("/img/**", "/bootstrap/**").permitAll()//同上
                .antMatchers("/admin/**").hasRole("ADMIN")//只有"ADMIN"角色才能访问该目录
                .antMatchers("/user/**").hasRole("USER")//只有"USER"角色才能访问该目录
                .anyRequest()
                .authenticated();
//                .and()
//                .csrf().disable();

        http.formLogin()
                .usernameParameter("id")
                .passwordParameter("password")

                .loginPage("/toLoginPage")//第一个· 登录页面
                .loginProcessingUrl("/login")//第二个登录表单提交url 登录接口
           /*     .successForwardUrl("/index")
                .defaultSuccessUrl("/index")*/

                // 登录成功的处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        User user = (User) authentication.getPrincipal();
                        req.getSession().setAttribute("userId", user.getUsername());
                        map.put("status", 200);
                        map.put("msg", authentication.getPrincipal());
                       /* out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();*/
                        resp.sendRedirect("/index");
                    }
                })

                // 登录失败的处理器
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);
                        if (e instanceof LockedException) {
                            map.put("msg", "账户被锁定，登录失败!");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "用户名或密码输入错误，登录失败!");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账户被禁用，登录失败!");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账户过期，登录失败!");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "密码过期，登录失败!");
                        } else {
                            map.put("msg", "登录失败!");
                        }
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                });
        // 和表单登录相关的接口统统都直接通过
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/toLoginPage") //注销成功后 到那个页面；


                // 登出成功的处理器
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "注销登录成功!");
                        /*out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();*/
                        resp.sendRedirect("/index");
                    }
                })
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                // 无访问权限的处理器
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 403);
                        map.put("msg", "无访问权限!");
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();

                    }
                });
        // 默认情况下用户直接访问一个需要认证之后才可以访问的请求时，会被重定向到.loginPage("/login")，前后端分离时会导致跨域。
        // 增加如下配置后，就不会发生重定向操作了，服务端会直接给浏览器一个 JSON 提示
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("status", 401);
//                        if (authException instanceof InsufficientAuthenticationException) {
//                            map.put("msg", "访问失败，请先登录!");
//                        } else {
//                            map.put("msg", "访问失败!");
//                        }
//                        out.write(new ObjectMapper().writeValueAsString(map));
//                        out.flush();
//                        out.close();
//                    }
//                });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置不需要拦截的请求地址，即该地址不走 Spring Security 过滤器链
//        web.ignoring().antMatchers("/toLoginPage");
        web.ignoring().antMatchers("/toLoginPage", "/toRegisterPage", "/User/user_Register", "/User/*", "/static/**");

    }


}

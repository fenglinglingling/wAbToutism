package com.abs.config;

import com.abs.mapper.UserMapper;
import com.abs.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        User user;
        if (id.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")){//邮箱
            user=userMapper.getUserByEmail(id);
        }else if (id.matches("^1[3-9]\\d{9}$")) {
            user = userMapper.getUserByPhone(id);
        }else {
             user=userMapper.getUserById(id);
        }

        String role="ROLE_" + user.getUser_role().toUpperCase();
        list.add(new SimpleGrantedAuthority(role));//角色列表
        return new org.springframework.security.core.userdetails.User(user.getUser_id(),user.getUser_password(), list);
    }
}


package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2021-05-26 15:41
 */
@Data
@Accessors(chain = true)
public class MyUser implements UserDetails {

    private Long id;

    @NotBlank(message = "账号不能为空！")
    private String username;

    @NotBlank(message = "密码不能为空！")
    private String password;

    private Integer sex;

    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

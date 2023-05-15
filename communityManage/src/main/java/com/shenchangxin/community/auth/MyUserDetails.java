package com.shenchangxin.community.auth;


import com.shenchangxin.community.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*        return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));*/
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<String> roleStringList = new ArrayList<>();
        roleStringList.add("ROLE_ADMIN");
        roleStringList.add("ROLE_USER");
        for(String roleString:roleStringList){
            grantedAuthorityList.add(new SimpleGrantedAuthority(roleString));
        }
        return grantedAuthorityList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
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


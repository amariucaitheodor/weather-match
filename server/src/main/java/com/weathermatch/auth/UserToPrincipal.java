package com.weathermatch.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

// Spring relies on principals, not users, so this class provides translation: takes in User class, spits out Principal class
public class UserToPrincipal implements UserDetails {

    private User user;
    private List<AuthUserGroup> authUserGroups;

    public UserToPrincipal(User user, List<AuthUserGroup> authUserGroups){
        super();
        this.user = user;
        this.authUserGroups = authUserGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authUserGroups ==null){
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        authUserGroups.forEach(group -> grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup())));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    //TODO: check if suitable for production
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

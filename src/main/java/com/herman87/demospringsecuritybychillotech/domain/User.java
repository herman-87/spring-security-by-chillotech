package com.herman87.demospringsecuritybychillotech.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer id;
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_email")
    private String email;
    @Column(name = "c_password")
    private String password;
    @Builder.Default
    @Column(name = "c_activate")
    private boolean isActivate = false;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_role", referencedColumnName = "c_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" +role.getName().name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActivate;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActivate;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActivate;
    }

    @Override
    public boolean isEnabled() {
        return isActivate;
    }
}

package com.zhaozhiguang.component.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 用户领域对象
 * @author zhaozhiguang
 */
@Data
@Entity
@Table(name = "sys_user", indexes = { @Index(name = "user_name_index", columnList = "user_name")})
public class SysUser implements UserDetails {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "pass_word", nullable = false)
    private String passWord;

    /**
     * 头像
     */
    @Column(name = "avatar", nullable = false)
    private String avatar;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    /**
     * 权限
     */
    @Transient
    private List<GrantedAuthority> authorities;

    /**
     * 状态
     */
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.Normal;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status == Status.AccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == Status.AccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status == Status.CredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return status == Status.Enabled;
    }

    /**
     * 用户状态
     */
    public enum Status {
        /**
         * 正常
         */
        Normal,
        /**
         * 禁用
         */
        Enabled,
        /**
         * 账号过期
         */
        AccountNonExpired,
        /**
         * 账号锁定
         */
        AccountNonLocked,
        /**
         * 凭证过期
         */
        CredentialsNonExpired
    }
}

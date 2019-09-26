package com.zhaozhiguang.component.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 用户领域对象
 * @author zhaozhiguang
 */
@Data
@Entity
@Table(name = "sys_user", indexes = { @Index(name = "user_name_index", columnList = "user_name")})
@SQLDelete(sql = "update sys_user set status = 1 where id = ?")
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
     * 权限(SpringSecurity控制)
     */
    @Transient
    @JsonIgnore
    private List<GrantedAuthority> authorities;

    /**
     * 角色
     */
    @Transient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRole> roles;

    /**
     * 权限(前端显示)
     */
    @Transient
    private Set<SysPermissions> permissions;

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

    @PrePersist
    public void persist () {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void update () {
        this.updateTime = LocalDateTime.now();
    }
}

package com.zhaozhiguang.component.security.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 角色
 *
 */
@Data
@Entity
@Table(name = "sys_role")
@Where(clause = "status = 0")
@SQLDelete(sql = "update sys_role set status = 1 where id = ?")
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名
     */
    @Column(name = "role_name", nullable = false)
    private String roleName;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private Integer status = 0;

    /**
     * 排序
     */
    @Column(name = "order_by", nullable = false)
    private Integer orderBy = 0;

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

    @Transient
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<SysUser> users;

    @Transient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permissions",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permissions_id")})
    private Set<SysPermissions> permissions;

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

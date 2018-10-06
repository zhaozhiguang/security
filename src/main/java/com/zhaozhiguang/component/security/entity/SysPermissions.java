package com.zhaozhiguang.component.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 权限
 *
 */
@Data
@Entity
@Table(name = "sys_permissions", indexes = { @Index(name = "parent_id_index", columnList = "parent_id")})
public class SysPermissions implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名
     */
    @Column(name = "permissions_name", nullable = false)
    private String permissionsName;

    /**
     * 权限标识
     */
    @Column(name = "permissions_tag")
    private String permissionsTag;

    /**
     * 权限url
     */
    @Column(name = "url")
    private String url;

    /**
     * 父级Id
     */
    @Column(name = "parent_id", nullable = false)
    private Integer parentId = 0;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Type type = Type.MENU;

    /**
     * 图标
     */
    @Column(name = "icon_style")
    private String iconStyle;

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

    @Override
    public String getAuthority() {
        return permissionsTag;
    }

    /**
     * 类型
     */
    public enum Type {
        /**
         * 菜单
         */
        MENU,
        /**
         * url
         */
        URL,
        /**
         * 按钮
         */
        BUTTON
    }

}

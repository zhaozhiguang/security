/**
  创建用户表
 */
drop table if exists sys_user;
create table sys_user (
  id int(11) auto_increment not null,
  user_name varchar(255) not null comment '用户名',
  pass_word varchar(255) not null comment '密码',
  avatar varchar(255) not null comment '头像',
  create_time datetime(6) not null comment '创建时间',
  update_time datetime(6) not null comment '修改时间',
  status tinyint not null default 0 comment '状态,详见代码',
  primary key (id),
  unique key `user_name_uk` (`user_name`)
) engine=InnoDB auto_increment=27 default charset=utf8 comment='用户表';

/**
  创建角色表
 */
drop table if exists sys_role;
create table sys_role (
  id int(11) auto_increment not null,
  role_name varchar(255) not null comment '角色名',
  order_by int(11) not null comment '排序',
  create_time datetime(6) not null comment '创建时间',
  update_time datetime(6) not null comment '修改时间',
  status tinyint not null default 0 comment '状态0:生效,1:失效',
  primary key (id)
) engine=InnoDB auto_increment=27 default charset=utf8 comment='角色表';

/**
  创建用户角色关系表
 */
drop table if exists sys_user_role;
create table sys_user_role (
  id int(11) auto_increment not null,
  user_id int(11) not null comment '用户id',
  role_id int(11) not null comment '角色id',
  primary key (id),
  key (user_id)
) engine=InnoDB auto_increment=27 default charset=utf8 comment='用户角色关系表';

/**
  创建权限表
 */
drop table if exists sys_permissions;
create table sys_permissions (
  id int(11) auto_increment not null,
  permissions_name varchar(255) not null comment '权限名',
  permissions_tag varchar(255) comment '权限标识',
  url varchar(255) comment '权限url',
  parent_id int(11) not null default 0 comment '父级id',
  type tinyint not null default 0 comment '类型,详见代码',
  icon_style varchar(255) comment '图标样式',
  order_by int(11) not null comment '排序',
  create_time datetime(6) not null comment '创建时间',
  update_time datetime(6) not null comment '修改时间',
  status tinyint not null default 0 comment '状态0:生效,1:失效',
  primary key (id),
  key (parent_id)
) engine=InnoDB auto_increment=27 default charset=utf8 comment='权限表';

/**
  创建角色权限关系表
 */
drop table if exists sys_role_permissions;
create table sys_role_permissions (
  id int(11) auto_increment not null,
  role_id int(11) not null comment '角色id',
  permissions_id int(11) not null comment '权限id',
  primary key (id),
  key (role_id)
) engine=InnoDB auto_increment=27 default charset=utf8 comment='角色权限关系表';

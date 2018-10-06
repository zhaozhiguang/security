insert into sys_user (id, user_name, pass_word, avatar, create_time, update_time, status)
  values (1, 'admin', '$2a$10$PCZ25pbjvz3UQFHP4PRqSu4Ciz3jdX/cjgydu1mqJ5e1tI0GYhUd.', 'https://www.baidu.com/img/bd_logo1.png?where=super', now(), now(), 0);
insert into sys_user (id, user_name, pass_word, avatar, create_time, update_time, status)
  values (2, 'test', '$2a$10$PCZ25pbjvz3UQFHP4PRqSu4Ciz3jdX/cjgydu1mqJ5e1tI0GYhUd.', 'https://www.baidu.com/img/bd_logo1.png?where=super', now(), now(), 1);
insert into sys_user (id, user_name, pass_word, avatar, create_time, update_time, status)
  values (3, 'sysUser', '$2a$10$PCZ25pbjvz3UQFHP4PRqSu4Ciz3jdX/cjgydu1mqJ5e1tI0GYhUd.', 'https://www.baidu.com/img/bd_logo1.png?where=super', now(), now(), 0);

insert into sys_role (id, role_name, status, order_by, create_time, update_time)
  values (1, '系统管理员', 0, 0, now(), now());
insert into sys_role (id, role_name, status, order_by, create_time, update_time)
  values (2, '一级管理员', 0, 0, now(), now());
insert into sys_role (id, role_name, status, order_by, create_time, update_time)
  values (3, '二级管理员', 0, 0, now(), now());

insert into sys_permissions (id, permissions_name, permissions_tag, url, parent_id, type, icon_style, status, order_by, create_time, update_time)
  values (1, '用户管理', 'sysUser:view', '/sysUser', 0, 0, 'example', 0, 0, now(), now());
insert into sys_permissions (id, permissions_name, permissions_tag, url, parent_id, type, icon_style, status, order_by, create_time, update_time)
  values (2, '用户管理', 'sysUser:view', '/sysUser1', 1, 1, 'example', 0, 0, now(), now());
insert into sys_permissions (id, permissions_name, permissions_tag, url, parent_id, type, icon_style, status, order_by, create_time, update_time)
  values (3, '查看', 'sysUser:view', '/sysUser2', 2, 2, null, 0, 0, now(), now());
insert into sys_permissions (id, permissions_name, permissions_tag, url, parent_id, type, icon_style, status, order_by, create_time, update_time)
  values (4, '添加', 'sysUser:create', '/sysUser3', 2, 2, null, 0, 0, now(), now());
insert into sys_permissions (id, permissions_name, permissions_tag, url, parent_id, type, icon_style, status, order_by, create_time, update_time)
  values (5, '修改', 'sysUser:update', '/sysUser4', 2, 2, null, 0, 0, now(), now());
insert into sys_permissions (id, permissions_name, permissions_tag, url, parent_id, type, icon_style, status, order_by, create_time, update_time)
  values (6, '删除', 'sysUser:delete', '/sysUser5', 2, 2, null, 0, 0, now(), now());

insert into sys_user_role (id, user_id, role_id)
  values (1, 1, 1);
insert into sys_user_role (id, user_id, role_id)
  values (2, 1, 2);
insert into sys_user_role (id, user_id, role_id)
  values (3, 3, 2);

insert into sys_role_permissions (id, role_id, permissions_id)
  values (1, 1, 1);
insert into sys_role_permissions (id, role_id, permissions_id)
  values (2, 1, 2);
insert into sys_role_permissions (id, role_id, permissions_id)
  values (3, 1, 3);
insert into sys_role_permissions (id, role_id, permissions_id)
  values (4, 1, 4);
insert into sys_role_permissions (id, role_id, permissions_id)
  values (5, 1, 5);
insert into sys_role_permissions (id, role_id, permissions_id)
  values (6, 2, 6);
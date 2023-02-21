create schema if not exists diploma_cloud;


insert into t_user(login, password) values ('neo8745', '12345678');
insert into t_user(login, password) values ('panya12', '1111222');
insert into t_user(login, password) values ('meloman', '12344321');
#
# insert into user_roles(user_id, roles) values (1, 'ADMIN');
# insert into user_roles(user_id, roles) values (2, 'READ');
# insert into user_roles(user_id, roles) values (3, 'WRITE');
# insert into user_roles(user_id, roles) values (2, 'WRITE');

# select u.login, ur.roles from users u join user_roles ur on u.id = ur.user_id where login='neo8745';
# select login, password, 'true' from users where login=? and password=?;

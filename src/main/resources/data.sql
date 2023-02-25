create schema if not exists diploma_cloud;


insert into users(login, password)
values ('neo8745@yandex.ru', '$2a$10$ciFUmq726O5V0Tnu37uEy.I7nhPXWO/WFN8m7HjNOphZFcOQcrl8a');
insert into users(login, password)
values ('panya12@yandex.ru', '$2a$10$L4cA.wDXaxBESV/FUGchT.WyEFX6qgMrdGGjDl7kt9QMFVWobi5Ne');
insert into users(login, password)
values ('meloman7575@yandex.ru', '$2a$10$fHYr2Lk2.KwBM4EFgQt97eb0rfOSVFl438DZbYIuE/hXlFL9mH6XG');

insert into user_entity_roles(user_entity_id, roles)
values (1, 'ROLE_ADMIN');
insert into user_entity_roles(user_entity_id, roles)
values (1, 'ROLE_WRITE');
insert into user_entity_roles(user_entity_id, roles)
values (2, 'ROLE_WRITE');
insert into user_entity_roles(user_entity_id, roles)
values (3, 'ROLE_READE');


select * from cloud_file c where c.user_id = :userId;

update cloud_file

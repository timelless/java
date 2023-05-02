insert into user_details (id, birth_date, name) values (1001, current_date(), 'vasilk');
insert into user_details (id, birth_date, name) values (1002, current_date(), 'vasilk2');
insert into user_details (id, birth_date, name) values (1003, current_date(), 'vasilk3');

insert into post (id, description, user_id) values (1001, 'Manually inserted post', 1001);
insert into post (id, description, user_id) values (1002, 'Manually inserted post for another user', 1003);
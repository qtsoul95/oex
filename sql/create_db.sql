
-- create table
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` BIGINT not null AUTO_INCREMENT comment 'user ID'
  , `username` VARCHAR(256) not null comment 'username'
  , `password` VARCHAR(256) not null comment 'password'
  , `role_json` VARCHAR(1024) not null COLLATE 'ascii_bin' comment 'user role json'
  , `created_at` DATETIME default NOW() not null comment 'created time'
  , `updated_at` DATETIME default NOW() ON UPDATE NOW() not null comment 'last updated time'
  , constraint `users_PK` primary key (`username`)
);

alter table `users` add unique `users_IX1` (`username`) ;

drop table if exists hunters CASCADE;
create table hunters (id integer auto_increment, echoes integer, name varchar(255), old_blood_feared boolean, primary key (id));
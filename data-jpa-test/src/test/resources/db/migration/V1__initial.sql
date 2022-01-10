create sequence hibernate_sequence start 1 increment 1 ;
create table if not exists food (
    id int not null auto_increment primary key,
    title varchar not null,
    price int
)
create database webdb;
drop database webdb;

create user web@localhost identified by 'pass';
grant all privileges on webdb.* to web@localhost;

use webdb;
create table products(
	code int auto_increment primary key,
    name varchar(100) not null,
    price int default 0,
    rdate datetime default now()
);

desc products;
insert into products(name, price)
values('냉장고', 3000000);
insert into products(name, price)
values('세탁기', 2400000);
insert into products(name, price)
values('에어컨', 2800000);

select *, format(price,0) fprice, date_format(rdate, '%Y-%m-%d %T') fdate
from products;

insert into products(name, price)
select name, price from products;

update products 
set name='비스코프 스타일러', price=1800000
where code !=3;


delete from products where code > 4;

update products 
set price = price * 0.1
where code !=3;
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

select * from products;
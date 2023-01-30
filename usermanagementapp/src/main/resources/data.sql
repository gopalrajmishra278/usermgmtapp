


`country_master`

INSERT INTO country_master values(1, `India`);
INSERT INTO country_master values(1, `Austrilla`);

select * from country_master;

`state_master`

insert into state_master values(1, 1, 'Madhya Pradesh');
insert into state_master values(2, 1, 'Jharkhand');
insert into state_master values(3, 2, 'Sydney');
insert into state_master values(4, 2, 'Canberra');

select*from state_master;

`city_master`

insert into  city_master values(1, 1, 'Jabalpur');
insert into city_master values(2, 1, 'Bhopal');
insert into city_master values(3, 2, 'Palamu');
insert into city_master values(4, 2, 'Gharwa');
insert into city_master values(5, 3, 'Elizabeth');
insert into city_master values(6, 3, 'Rose Bay');
insert into city_master values(7, 4, 'Gharwa');
insert into city_master values(8, 4, 'Gharwa');

select*from city_master;

commit;
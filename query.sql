insert into users (id, username, password, description, role, active) values
(1, 'admin', 'c93ccd78b2076528346216b3b2f701e6', 'ADMIN', 0, 1),
(2, 'phong1', '3b497472e008317da4eca3dbc1aa40ec', 'Phong tro 1', 1, 1),
(3, 'phong2', '1f9e8d0de6c10de179d02ba14a6217ba', 'Phong tro 2', 1, 1);

select * from users;

insert into home_price(user_id, room_price, electric_price, water_price, 
internet_price, garbage_price, living_price, total, status, created_at, deposited_at, note)
values
(2, '1500000', '250000', '50000', '50000', '40000', '20000', '1910000', 1, CAST('2022-03-01' as date), null, 'NOTE'),
(2, '1400000', '250000', '50000', '50000', '40000', '20000', '1810000', 1, CAST('2022-02-01' as date), null, 'NOTE'),
(2, '1600000', '250000', '50000', '50000', '40000', '20000', '2010000', 1, CAST('2022-01-01' as date), null, 'NOTE'),
(2, '1500000', '250000', '50000', '50000', '40000', '20000', '1510000', 1, CAST('2021-12-01' as date), null, 'NOTE'),
(2, '1400000', '250000', '50000', '50000', '40000', '20000', '1300000', 1, CAST('2021-11-01' as date), null, 'NOTE'),
(2, '1600000', '250000', '50000', '50000', '40000', '20000', '1700000', 1, CAST('2021-10-01' as date), null, 'NOTE');

delete from home_price;

select * from home_price;

SELECT * FROM 
( SELECT a.*, ROW_NUMBER() OVER(ORDER BY a.created_at DESC) AS rownumber from home_price a 
INNER JOIN users b ON a.user_id = b.id WHERE b.id = 2 ) c
WHERE c.rownumber BETWEEN 1 AND 2;
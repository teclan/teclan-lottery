-- 双色球表结构(原始出球顺序)
create table ssq (
 issue varchar(8) primary key,
 openTime varchar(16) ,
 r1 int,
 r2 int,
 r3 int,
 r4 int,
 r5 int,
 r6 int,
 blue int
);
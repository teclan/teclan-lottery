-- 大乐透表结构(原始出球顺序)
create table dlt (
 issue varchar(8) primary key,
 openTime varchar(16) ,
 r1 int,
 r2 int,
 r3 int,
 r4 int,
 r5 int,
 b1 int,
 b2 int
);
-- 双色球表结构(球号小到大排序)
create table ssq_sort (
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
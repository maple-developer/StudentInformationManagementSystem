create database if not exists db_web;
use db_web;

# create student table
create table if not exists tb_stu
(
    id      int primary key auto_increment,
    name    varchar(20) not null,
    age     int         not null,
    academy varchar(50) not null,
    grade   varchar(15) not null
);

# create user for login table
create table if not exists tb_user
(
    id       int primary key auto_increment,
    username varchar(50)  not null unique,
    passwd   varchar(50)  not null,
    email    varchar(100) not null unique
);

create table if not exists tb_student
(
    id        int primary key auto_increment,
    stu_id    varchar(13) not null unique,
    name      varchar(20) not null,
    age       int         not null,
    academy   varchar(50) not null,
    grade     varchar(15) not null,
    specialty varchar(50) not null,
    contact   varchar(20) not null,
    status    varchar(50) not null
);

####
# insert statement
####
insert into tb_stu (name, age, academy, grade)
values ('李清照', 20, '婉约派', '2019');
insert into tb_stu (name, age, academy, grade)
values ('苏轼', 20, '豪放派', '2019');

insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300001', '宁凌晗', 22, '玄武院', '2019', '玄武', 'xxxxxxxxxxx', '在读');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300002', '萧晨', 35, '仙武院', '2019', '仙武同修', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300003', '张三丰', 150, '仙人院', '2019', '仙人', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300004', '张之唯', 200, '仙人院', '2019', '仙人', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300005', '张楚岚', 20, '异人院', '2019', '异人', 'xxxxxxxxxxx', '在读');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300006', '辛弃疾', 35, '诗人院', '2019', '诗人', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300007', '岳飞', 35, '军武院', '2019', '军官', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300008', '秦桧', 45, '小人院', '2019', '小人', 'xxxxxxxxxxx', '延迟毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300009', '李白', 35, '诗人院', '2019', '诗人', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300010', '荆轲', 28, '暗影院', '2019', '刺客', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300011', '韩非子', 25, '法', '2019', '法治', 'xxxxxxxxxxx', '在读');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300012', '朱元璋', 80, '皇帝院', '2019', '皇帝', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300013', '朱棣', 60, '皇帝院', '2019', '皇帝', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300014', '韩立', 28, '仙人院', '2019', '仙人', 'xxxxxxxxxxx', '在读');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300015', '南宫婉', 40, '仙人院', '2019', '仙人', 'xxxxxxxxxxx', '在读');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300016', '陈巧倩', 25, '仙人院', '2019', '仙人', 'xxxxxxxxxxx', '在读');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300017', '墨大夫', 55, '凡人院', '2019', '凡人', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300018', '张铁', 19, '凡人院', '2019', '凡人', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300019', '曲魂', 35, '傀儡院', '2019', '傀儡', 'xxxxxxxxxxx', '在读');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300020', '秦始皇', 75, '皇帝院', '2019', '皇帝', 'xxxxxxxxxxx', '毕业');
insert into tb_student (stu_id, name, age, academy, grade, specialty, contact, status)
values ('2030110300021', '李化元', 45, '仙人院', '2019', '仙人', 'xxxxxxxxxxx', '在毕业');


####
# select statement
####
select *
from tb_user;
select *
from tb_stu;

select count(*)
from tb_student;
select *
from tb_student;
select *
from tb_student
where stu_id like concat('2030', '%');
select *
from tb_student
where stu_id like '%_%';
select *
from tb_student
where age = 22;


####
# drop statement
####
drop table tb_user;
drop table tb_student;

####
# delete statement
####
delete
from tb_stu
where id = 5;

####
# update statement
####
update tb_stu
set academy = '武神院'
where id = 1;

update tb_student
set stu_id    = '2030110300001',
    name      = '宁凌晗',
    age       = '22',
    academy   = '仙武院',
    grade     = '2019',
    specialty = '玄幻',
    contact   = 'xxxxxxxxxxxx',
    status    = '在读'
where id = 1;

# Pagination
select *
from tb_student
limit 0, 2;
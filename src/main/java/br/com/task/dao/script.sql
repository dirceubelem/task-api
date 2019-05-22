drop table task;
drop table status;
drop table project;
drop table account;

create table account
(
    id        varchar(22)  not null,
    name      varchar(50)  not null,
    email     varchar(100) not null,
    password  varchar(40)  not null,
    token     varchar(22),
    active    boolean      not null default true,
    createdat timestamp    not null,
    expiredat timestamp,
    constraint pk_account primary key (id)
);

insert into account
values ('5C7M3JEI468RO1D9VVV2U2', 'Dirceu Belem', 'dirceubelem@gmail.com', '2e6f9b0d5885b6010f9167787445617f553a735f',
        null, true, now(), null);

create table status
(
    id   int         not null,
    name varchar(40) not null,
    constraint pk_status primary key (id)
);

insert into status
values (1, 'Backlog');
insert into status
values (2, 'In progress');
insert into status
values (3, 'Test');
insert into status
values (4, 'Done');

create table project
(
    id             varchar(22) not null,
    idaccountowner varchar(22) not null,
    name           varchar(50) not null,
    createdat      timestamp   not null,
    active         boolean     not null,
    constraint pk_project primary key (id),
    constraint fk_project_account foreign key (idaccountowner) references account (id)
);

create table task
(
    id            varchar(22)  not null,
    idproject     varchar(22)  not null,
    idstatus      int          not null,
    idaccountfrom varchar(22)  not null,
    idaccountto   varchar(22)  not null,
    name          varchar(300) not null,
    description   varchar(5000),
    tags          varchar(300),
    estimate      double precision, -- hours
    createdat     timestamp    not null,
    startedat     timestamp,
    deliveredat   timestamp,
    priority      int,
    constraint pk_task primary key (id),
    constraint fk_task_project foreign key (idproject) references project (id),
    constraint fk_task_status foreign key (idstatus) references status (id),
    constraint fk_task_account_01 foreign key (idaccountto) references account (id),
    constraint fk_task_account_02 foreign key (idaccountfrom) references account (id)
);

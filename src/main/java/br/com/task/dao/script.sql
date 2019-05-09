drop table account;

create table account (
     id varchar(22) not null,
     name varchar(50) not null,
     email varchar(100) not null,
     password varchar(40) not null,
     token varchar(22),
     active boolean not null default true,
     createdat timestamp not null,
     expiredat timestamp
);

alter table account add constraint pk_account primary key (id)

insert into account values ('5C7M3JEI468RO1D9VVV2U2', 'Dirceu Belem', 'dirceubelem@gmail.com', '2e6f9b0d5885b6010f9167787445617f553a735f', null, true, now(), null);
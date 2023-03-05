create table "person"
(
    "id"             bigint not null,
    "firstname"      varchar(255) default null,
    "lastname"       varchar(255) default null,
    "status"         varchar(255) default null,
    "fitness"        date         default null,
    "lifeguard"      varchar(255) default null,
    "lifeguard_from" date         default null
);
insert into "person" ("id",
                      "firstname",
                      "lastname",
                      "status",
                      "fitness",
                      "lifeguard",
                      "lifeguard_from")
values (2,
        'Bilbo',
        'Baggins',
        'ACTIVE',
        null,
        null,
        null),
       (3,
        'Frodo',
        'Baggins',
        'ILL',
        null,
        null,
        null),
       (2007,
        'Test',
        'Hase',
        'ACTIVE',
        '2020-08-02',
        'BRONZE',
        '2022-09-01'),
       (2008,
        'Marie',
        'Clausen',
        'ACTIVE',
        '2022-06-06',
        'GOLD',
        '2016-05-06');
alter table "person"
    add primary key ("id");
commit;
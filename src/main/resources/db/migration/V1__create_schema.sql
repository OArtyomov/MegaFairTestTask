create table game
(
    id          serial constraint game_id_pk primary key,
    name        varchar not null,
    game_symbol varchar not null
        constraint game_symbol_unique
            unique
);

create table platform
(
    id   serial constraint platform_pk primary key,
    name varchar                                                not null
);

create table "user"
(
    id          serial constraint user_pk primary key,
    identifier  varchar not null,
    signature   varchar not null,
    platform_id integer not null
        constraint user_platform_id_fk
            references platform,
    constraint user_identifier_platform_id_unique
        unique (identifier, platform_id)
);


create table platform_game
(
    id          serial constraint platform_games_pk primary key,
    platform_id integer not null
        constraint platform_game_platform_id_fk
            references platform,
    game_id     integer not null
        constraint platform_game_game_id_fk
            references game,
    enabled     bit     not null,
    constraint platform_game_pk2
        unique (platform_id, game_id)
);


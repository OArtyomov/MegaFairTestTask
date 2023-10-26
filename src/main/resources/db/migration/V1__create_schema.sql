create table game
(
    id          bigserial constraint game_id_pk primary key,
    name        varchar not null,
    game_symbol varchar not null
        constraint game_symbol_unique
            unique
);

create table platform
(
    id   bigserial constraint platform_pk primary key,
    name varchar                                                not null,
    identifier varchar not null
    constraint platform_identifier_unique
        unique
);

create table "user"
(
    id          bigserial constraint user_pk primary key,
    identifier  varchar not null,
    signature   varchar not null,
    platform_id bigint not null
        constraint user_platform_id_fk
            references platform,
    constraint user_identifier_platform_id_unique
        unique (identifier, platform_id)
);


create table platform_game
(
    id          bigserial constraint platform_games_pk primary key,
    platform_id bigint not null
        constraint platform_game_platform_id_fk
            references platform,
    game_id     bigint not null
        constraint platform_game_game_id_fk
            references game,
    enabled     bit     not null,
    constraint platform_game_pk2
        unique (platform_id, game_id)
);


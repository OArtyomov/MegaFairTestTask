INSERT INTO game (id, name, game_symbol)
VALUES (1, 'BlackJack', 'BJ'),
       (2, 'Pocker', 'PC'),
       (3, 'Wheel of Fortune', 'WF');

INSERT INTO platform (id, name, identifier)
VALUES (1, 'Caesar Palace', 'CP'),
       (2, 'Dubay moll', 'DM'),
       (3, 'Atlatic city', 'AC');

INSERT INTO "user" (identifier, signature, platform_id)
VALUES ('AA', 'AA', 1),
       ('BB', 'BB', 2),
       ('CC', 'CC', 3);

INSERT INTO platform_game (id, platform_id, game_id, enabled)
VALUES (1, 1, 1, '1'),
       (2, 1, 2, '1'),
       (3, 1, 3, '1'),
       (4, 2, 2, '1'),
       (5, 3, 3, '1');

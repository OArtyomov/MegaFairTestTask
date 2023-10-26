INSERT INTO game (id, name, game_symbol)
VALUES (1, 'BlackJack', 'BJ'),
       (2, 'Pocker', 'PC'),
       (3, 'Wheel of Fortune', 'WF');

INSERT INTO platform (id, name)
VALUES (1, 'Caesar Palace'),
       (2, 'Dubay moll'),
       (3, 'Atlatic city');

INSERT INTO "user" (id, identifier, signature, platform_id)
VALUES (1, 'AA', 'AA', 1),
       (2, 'BB', 'BB', 2),
       (3, 'CC', 'CC', 3);

INSERT INTO platform_game (id, platform_id, game_id, enabled)
VALUES (1, 1, 1, '1'),
       (2, 1, 2, '1'),
       (3, 1, 3, '1'),
       (4, 2, 2, '1'),
       (5, 3, 3, '1');

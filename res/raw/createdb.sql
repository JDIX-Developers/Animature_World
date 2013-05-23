--TABLE CREATION--

CREATE TABLE ANIMATURE (
	"id" INTEGER NOT NULL PRIMARY KEY,
	"height" REAL NOT NULL,
	"weight" REAL NOT NULL,
	"type" INTEGER NOT NULL,
	"speed" INTEGER NOT NULL,
	"defense" INTEGER NOT NULL,
	"agility" INTEGER NOT NULL,
	"strenght" INTEGER NOT NULL,
	"precission" INTEGER NOT NULL,
	"health" INTEGER NOT NULL,
	"evo_level" INTEGER,
	"base_exp" INTEGER  NOT NULL,
	"capture_range" INTEGER NOT NULL);

CREATE TABLE ATTACK (
	"id" INTEGER NOT NULL PRIMARY KEY,
	"type" INTEGER NOT NULL,
	"max_pp" INTEGER NOT NULL,
	"attribute" INTEGER,
	"power" INTEGER NOT NULL,
	"probability" INTEGER NOT NULL,
	"isFirst" INTEGER NOT NULL);

CREATE TABLE CAPTURABLE (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	"animature" INTEGER NOT NULL REFERENCES ANIMATURE(id) ON DELETE CASCADE,
	"save" INTEGER REFERENCES SAVE(id) ON DELETE CASCADE,
	"nickname" TEXT,
	"sex" INTEGER NOT NULL,
	"status" INTEGER NOT NULL,
	"captured_time" INTEGER NOT NULL,
	"attack1" INTEGER REFERENCES ATTACK(id) ON DELETE SET NULL,
	"attack1_pp" INTEGER,
	"attack2" INTEGER REFERENCES ATTACK(id) ON DELETE SET NULL,
	"attack2_pp" INTEGER,
	"attack3" INTEGER REFERENCES ATTACK(id) ON DELETE SET NULL,
	"attack3_pp" INTEGER,
	"attack4" INTEGER REFERENCES ATTACK(id) ON DELETE SET NULL,
	"attack4_pp" INTEGER,
	"health" INTEGER NOT NULL,
	"level" INTEGER NOT NULL,
	"cur_exp" INTEGER NOT NULL,
	"exp" INTEGER NOT NULL,
	"box" INTEGER);

CREATE TABLE ITEM (
	"id" INTEGER NOT NULL PRIMARY KEY,
	"name" TEXT NOT NULL,
	"type" INTEGER NOT NULL,
	"description" TEXT NOT NULL);

CREATE TABLE ENEMY (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	"map" INTEGER NOT NULL,
	"coord_x" INTEGER NOT NULL,
	"coord_y" INTEGER NOT NULL,
	"money" INTEGER NOT NULL,
	"an1" INTEGER NOT NULL REFERENCES CAPTURABLE(id) ON DELETE SET NULL,
	"an2" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL,
	"an3" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL,
	"an4" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL,
	"an5" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL,
	"an6" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL);

CREATE TABLE USER (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	"email" TEXT NOT NULL,
	"password" TEXT NOT NULL,
	"username" TEXT NOT NULL,
	"is_current" INTEGER NOT NULL);

CREATE TABLE SAVE (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	"id_user" INTEGER NOT NULL REFERENCES USER(id) ON DELETE CASCADE,
	"player_name" TEXT NOT NULL,
	"sex" INTEGER  NOT NULL,
	"stage" INTEGER NOT NULL DEFAULT 0,
	"last_played" INTEGER NOT NULL,
	"start_date" INTEGER NOT NULL,
	"total_time" INTEGER NOT NULL,
	"steps" INTEGER DEFAULT 0,
	"an1" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL DEFAULT NULL,
	"an2" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL DEFAULT NULL,
	"an3" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL DEFAULT NULL,
	"an4" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL DEFAULT NULL,
	"an5" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL DEFAULT NULL,
	"an6" INTEGER REFERENCES CAPTURABLE(id) ON DELETE SET NULL DEFAULT NULL,
	"map" INTEGER NOT NULL,
	"coord_x" INTEGER NOT NULL,
	"coord_y" INTEGER NOT NULL,
	"neighbor" TEXT NOT NULL,
	"first_an" INTEGER NOT NULL REFERENCES ANIMATURE(id) ON DELETE SET NULL,
	"orientation" INTEGER NOT NULL,
	"last_healing_map" INTEGER DEFAULT NULL,
	"last_healing_x" INTEGER DEFAULT NULL,
	"last_healing_y" INTEGER DEFAULT NULL,
	"medals" INTEGER NOT NULL DEFAULT 0,
	"money" INTEGER NOT NULL DEFAULT 0,
	CHECK (last_healing_map IS NULL OR (last_healing_map IS NOT NULL AND last_healing_x IS NOT NULL AND last_healing_y IS NOT NULL)));

CREATE TABLE BAG (
	"save" INTEGER NOT NULL REFERENCES SAVE(id) ON DELETE CASCADE,
	"item" INTEGER NOT NULL REFERENCES ITEM(id) ON DELETE CASCADE,
	"quantity" INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY (save, item));

CREATE TABLE VIEWED (
	"save" INTEGER REFERENCES SAVE(id) ON DELETE CASCADE,
	"animature" INTEGER REFERENCES ANIMATURE(id) ON DELETE CASCADE,
	PRIMARY KEY (save, animature));

CREATE TABLE ANIMATURE_ATTACKS (
	"animature" INTEGER REFERENCES ANIMATURE(id) ON DELETE CASCADE,
	"attack" INTEGER REFERENCES ATTACK(id) ON DELETE CASCADE,
	"level" INTEGER,
	PRIMARY KEY (animature, attack));

CREATE TABLE FIGHT (
	"save" INTEGER NOT NULL REFERENCES SAVE(id) ON DELETE CASCADE,
	"enemy" INTEGER NOT NULL REFERENCES ENEMY(id) ON DELETE CASCADE,
	PRIMARY KEY (save, enemy));

--DATA INSERTION--

-- ANIMATURE (id, height, weight, type, speed, defense, agility, strenght, precission, health, evo_level, base_exp, capture_range)
-- Bulbasur-Ivysaur-Venusaur
INSERT INTO ANIMATURE VALUES (1, 0.7, 6.9, 136, 50, 60, 40, 65, 65, 20, 16, 64, 45);
INSERT INTO ANIMATURE VALUES (2, 1, 13, 136, 50, 60, 40, 65, 65, 20, 36, 141, 45);
INSERT INTO ANIMATURE VALUES (3, 2, 100, 136, 50, 60, 40, 65, 65, 20, -1, 208, 45);
-- Charmander-Charmeleon-Charizard
INSERT INTO ANIMATURE VALUES (4, 0.6, 8.5, 2, 65, 40, 65, 50, 60, 20, 16, 65, 45);
INSERT INTO ANIMATURE VALUES (5, 1.1, 19, 2, 65, 40, 65, 50, 60, 20, 36, 142, 45);
INSERT INTO ANIMATURE VALUES (6, 1.7, 90.5, 514, 65, 40, 65, 50, 60, 20, -1, 209, 45);
-- Squirtle-Wartortle-Blastoise
INSERT INTO ANIMATURE VALUES (7, 0.5, 9, 4, 65, 40, 65, 50, 60, 20, 16, 66, 45);
INSERT INTO ANIMATURE VALUES (8, 1, 22.5, 4, 65, 40, 65, 50, 60, 20, 36, 143, 45);
INSERT INTO ANIMATURE VALUES (9, 1.6, 85.5, 4, 65, 40, 65, 50, 60, 20, -1, 210, 45);
-- Caterpie-Metapod-Butterfree
INSERT INTO ANIMATURE VALUES (10, 0.3, 2.9, 2048, 40, 60, 60, 40, 75, 25, 8, 53, 255);
INSERT INTO ANIMATURE VALUES (11, 0.7, 9.9, 2048, 40, 60, 60, 40, 75, 25, 18, 72, 120);
INSERT INTO ANIMATURE VALUES (12, 1.1, 32, 2048, 40, 60, 60, 40, 75, 25, -1, 160, 45);
-- Weedle-Kakuna-Beedrill
INSERT INTO ANIMATURE VALUES (13, 0.3, 3.2, 2048, 40, 60, 60, 40, 75, 25, 8, 52, 255);
INSERT INTO ANIMATURE VALUES (14, 0.6, 10, 2048, 40, 60, 60, 40, 75, 25, 18, 71, 120);
INSERT INTO ANIMATURE VALUES (15, 1, 29.5, 2560, 40, 60, 60, 40, 75, 25, -1, 159, 45);
-- Pidgey-Pidgeotto-Pidgeot
INSERT INTO ANIMATURE VALUES (16, 0.3, 3.2, 513, 60, 40, 80, 40, 60, 20, 16, 55, 255);
INSERT INTO ANIMATURE VALUES (17, 0.6, 10, 513, 60, 40, 80, 40, 60, 20, 36, 113, 120);
INSERT INTO ANIMATURE VALUES (18, 1, 29.5, 513, 60, 40, 80, 40, 60, 20, -1, 172, 45);
-- Rattata-Raticate
INSERT INTO ANIMATURE VALUES (19, 0.3, 3.5, 512, 60, 40, 60, 60, 60, 20, 15, 57, 255);
INSERT INTO ANIMATURE VALUES (20, 0.7, 18.5, 512, 60, 40, 60, 60, 60, 20, -1, 116, 127);

-- ATTACK (id, type, max_pp, attributte, power, probability, isFirst)
INSERT INTO ATTACK VALUES (1, 1, 35, -1, 35, 95, 0);		-- PLACAJE
INSERT INTO ATTACK VALUES (2, 1, 30, -1, 40, 100, 1);		-- ATAQUE RAPIDO
INSERT INTO ATTACK VALUES (3, 1, 40, 3, 0, 100, 0);			-- GRUÑIDO
INSERT INTO ATTACK VALUES (4, 1, 30, 1, 0, 100, 0);			-- MALICIOSO
INSERT INTO ATTACK VALUES (5, 1, 35, -1, 40, 100, 0);		-- ARAÑAZO
INSERT INTO ATTACK VALUES (6, 2, 25, -1, 40, 100, 0);		-- ASCUAS
INSERT INTO ATTACK VALUES (7, 4, 20, -1, 65, 100, 0);		-- BURBUJA
INSERT INTO ATTACK VALUES (8, 8, 15, -1, 35, 100, 0);		-- LÁTIGO CEPA
INSERT INTO ATTACK VALUES (9, 4, 25, -1, 40, 100, 0);		-- PISTOLA AGUA
INSERT INTO ATTACK VALUES (10, 1, 35, -1, 35, 100, 0);		-- PICOTAZO

-- ANIMATURE ATTACKS (id_Animature, id_Attack, level)
-- Bulbasur-Ivysaur-Venusaur
INSERT INTO ANIMATURE_ATTACKS VALUES (1, 1, 2);		-- Bulbasur -> Placaje, 		Nivel 2
INSERT INTO ANIMATURE_ATTACKS VALUES (1, 2, 6);		-- Bulbasur -> Ataque rapido, 	Nivel 6
INSERT INTO ANIMATURE_ATTACKS VALUES (1, 3, 10);	-- Bulbasur -> Gruñido,			Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (1, 4, 14);	-- Bulbasur -> Malicioso,		Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (1, 8, 16);	-- Bulbasur -> Latigo Cepa,		Nivel 16
INSERT INTO ANIMATURE_ATTACKS VALUES (2, 8, 16);	-- Ivysaur  -> Latigo Cepa,		Nivel 16

-- Charmander-Charmeleon-Charizard
INSERT INTO ANIMATURE_ATTACKS VALUES (4, 5, 2);		-- Charmander -> Arañazo, 		Nivel 2
INSERT INTO ANIMATURE_ATTACKS VALUES (4, 3, 6);		-- Charmander -> Gruñido, 		Nivel 6
INSERT INTO ANIMATURE_ATTACKS VALUES (4, 2, 10);	-- Charmander -> Ataque rapido,	Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (4, 4, 14);	-- Charmander -> Malicioso,		Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (4, 6, 16);	-- Charmander -> Ascuas, 		Nivel 16
INSERT INTO ANIMATURE_ATTACKS VALUES (5, 6, 16);	-- Charmeleon -> Ascuas, 		Nivel 16

-- Squirtle-Wartortle-Blastoise
INSERT INTO ANIMATURE_ATTACKS VALUES (7, 7, 2);		-- Squirtle -> Burbuja, 		Nivel 2
INSERT INTO ANIMATURE_ATTACKS VALUES (7, 3, 6);		-- Squirtle -> Gruñido, 		Nivel 6
INSERT INTO ANIMATURE_ATTACKS VALUES (7, 2, 10);	-- Squirtle -> Ataque rapido,	Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (7, 4, 14);	-- Squirtle -> Malicioso,		Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (7, 9, 16);	-- Squirtle -> Pistola agua,	Nivel 16
INSERT INTO ANIMATURE_ATTACKS VALUES (8, 9, 16);	-- Wartortle -> Pistola agua,	Nivel 16

-- Caterpie-Metapod-Butterfree
INSERT INTO ANIMATURE_ATTACKS VALUES (10, 1, 2);	-- Caterpie -> Placaje, 		Nivel 2
INSERT INTO ANIMATURE_ATTACKS VALUES (10, 3, 6);	-- Caterpie -> Gruñido, 		Nivel 6
INSERT INTO ANIMATURE_ATTACKS VALUES (10, 10, 10);	-- Caterpie -> Picotazo,		Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (11, 10, 10);	-- Metapod  -> Picotazo,		Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (10, 4, 14);	-- Caterpie -> Malicioso,		Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (11, 4, 14);	-- Metapod  -> Malicioso,		Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (10, 2, 16);	-- Caterpie -> Ataque rapido,	Nivel 16
INSERT INTO ANIMATURE_ATTACKS VALUES (11, 2, 16);	-- Metapod  -> Ataque rapido,	Nivel 16

-- Weedle-Kakuna-Beedrill
INSERT INTO ANIMATURE_ATTACKS VALUES (13, 1, 2);	-- Weedle -> Placaje, 			Nivel 2
INSERT INTO ANIMATURE_ATTACKS VALUES (13, 3, 6);	-- Weedle -> Gruñido, 			Nivel 6
INSERT INTO ANIMATURE_ATTACKS VALUES (13, 10, 10);	-- Weedle -> Picotazo,			Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (14, 10, 10);	-- Kakuna -> Picotazo,	   	    Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (13, 4, 14);	-- Weedle -> Malicioso,			Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (14, 4, 14);	-- Kakuna -> Malicioso,			Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (13, 2, 16);	-- Weedle -> Ataque rapido,		Nivel 16
INSERT INTO ANIMATURE_ATTACKS VALUES (14, 2, 16);	-- Kakuna -> Ataque rapido,		Nivel 16

-- Pidgey-Pidgeotto-Pidgeot
INSERT INTO ANIMATURE_ATTACKS VALUES (16, 1, 2);	-- Pidgey 	 -> Placaje, 		Nivel 2
INSERT INTO ANIMATURE_ATTACKS VALUES (16, 3, 6);	-- Pidgey 	 -> Gruñido, 		Nivel 6
INSERT INTO ANIMATURE_ATTACKS VALUES (16, 2, 10);	-- Pidgey 	 -> Ataque rapido,	Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (16, 10, 14);	-- Pidgey 	 -> Picotazo,		Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (16, 5, 16);	-- Pidgey 	 -> Arañazo,		Nivel 16
INSERT INTO ANIMATURE_ATTACKS VALUES (17, 5, 16);	-- Pidgeotto -> Arañazo,		Nivel 16

-- Rattata-Raticate
INSERT INTO ANIMATURE_ATTACKS VALUES (19, 1, 2);	-- Caterpie -> Placaje, 		Nivel 2
INSERT INTO ANIMATURE_ATTACKS VALUES (19, 3, 6);	-- Caterpie -> Gruñido, 		Nivel 6
INSERT INTO ANIMATURE_ATTACKS VALUES (19, 2, 10);	-- Caterpie -> Ataque rapido,	Nivel 10
INSERT INTO ANIMATURE_ATTACKS VALUES (19, 4, 14);	-- Caterpie -> Malicioso,		Nivel 14
INSERT INTO ANIMATURE_ATTACKS VALUES (19, 5, 16);	-- Caterpie -> Arañazo,			Nivel 16
INSERT INTO ANIMATURE_ATTACKS VALUES (20, 5, 16);	-- Metapod  -> Arañazo,			Nivel 16
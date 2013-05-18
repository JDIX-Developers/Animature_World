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
	"probability" INTEGER NOT NULL);

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
	"user" INTEGER NOT NULL REFERENCES USER(id) ON DELETE CASCADE,
	"character" INTEGER NOT NULL,
	"stage" INTEGER NOT NULL DEFAULT 0,
	"last_played" INTEGER DEFAULT (strftime('%s', 'now')),
	"started" INTEGER DEFAULT (strftime('%s', 'now')),
	"total_time" INTEGER DEFAULT 0,
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
	"neighbour" INTEGER NOT NULL,
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
INSERT INTO ANIMATURE VALUES (1, 0.7, 6.9, 3, 100, 120, 100, 120, 100, 80, 16, 150, 10);
INSERT INTO ANIMATURE VALUES (2, 1, 13, 3, 100, 120, 100, 120, 100, 80, 36, 500, 10);
INSERT INTO ANIMATURE VALUES (3, 2, 100, 3, 100, 120, 100, 120, 100, 80, -1, 1200, 10);

INSERT INTO ANIMATURE VALUES (4, 0.6, 8.5, 1, 100, 120, 100, 120, 100, 80, 16, 150, 10);
INSERT INTO ANIMATURE VALUES (5, 1.1, 19, 1, 100, 120, 100, 120, 100, 80, 36, 500, 10);
INSERT INTO ANIMATURE VALUES (6, 1.7, 90.5, 1, 100, 120, 100, 120, 100, 80, -1, 1200, 10);

INSERT INTO ANIMATURE VALUES (7, 0.5, 9, 2, 100, 120, 100, 120, 100, 80, 16, 150, 10);
INSERT INTO ANIMATURE VALUES (8, 1, 22.5, 2, 100, 120, 100, 120, 100, 80, 36, 500, 10);
INSERT INTO ANIMATURE VALUES (9, 1.6, 85.5, 2, 100, 120, 100, 120, 100, 80, -1, 1200, 10);

INSERT INTO ANIMATURE VALUES (10, 0.3, 2.9, 11, 100, 120, 100, 120, 100, 80, 8, 150, 10);
INSERT INTO ANIMATURE VALUES (11, 0.7, 9.9, 11, 100, 120, 100, 120, 100, 80, 18, 500, 10);
INSERT INTO ANIMATURE VALUES (12, 1.1, 32, 11, 100, 120, 100, 120, 100, 80, -1, 1200, 10);

INSERT INTO ANIMATURE VALUES (13, 0.3, 3.2, 11, 100, 120, 100, 120, 100, 80, 8, 150, 10);
INSERT INTO ANIMATURE VALUES (14, 0.6, 10, 11, 100, 120, 100, 120, 100, 80, 18, 500, 10);
INSERT INTO ANIMATURE VALUES (15, 1, 29.5, 11, 100, 120, 100, 120, 100, 80, -1, 1200, 10);

--INSERT INTO ANIMATURE VALUES (16, 0.3, 3.2, 0, 9, 100, 120, 100, 120, 100, 80, 16, 150);
--INSERT INTO ANIMATURE VALUES (17, 0.6, 10, 0, 9, 100, 120, 100, 120, 100, 80, 36, 500);
--INSERT INTO ANIMATURE VALUES (18, 1, 29.5, 0, 9, 100, 120, 100, 120, 100, 80, -1, 1200);

--INSERT INTO ANIMATURE VALUES (19, 0.3, 3.5, 0, -1, 100, 120, 100, 120, 100, 80, 12, 150);
--INSERT INTO ANIMATURE VALUES (20, 0.7, 18.5, 0, -1, 100, 120, 100, 120, 100, 80, 25, 500);

--INSERT INTO ATTACK VALUES (1, 0, 35, 1, -1, 35, 95);
--INSERT INTO ATTACK VALUES (2, 1, 25, 1, -1, 40, 100);
--INSERT INTO ATTACK VALUES (3, 2, 25, 1, -1, 40, 100);
--INSERT INTO ATTACK VALUES (4, 3, 15, 1, -1, 35, 100);
--INSERT INTO ATTACK VALUES (5, 0, 30, 1, -1, 40, 100);
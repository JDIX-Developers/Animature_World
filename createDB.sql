PRAGMA encoding = "UTF-8";
PRAGMA foreign_keys = ON;

CREATE TABLE ANIMATURE (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT, 
	"name" TEXT, 
	"height" INTEGER, 
	"weight" INTEGER, 
	"type" INTEGER, 
	"type2" INTEGER, 
	"description" TEXT,
	"speed" INTEGER, 
	"defense" INTEGER, 
	"agility" INTEGER, 
	"strenght" INTEGER, 
	"precission" INTEGER, 
	"health" INTEGER, 
	"level_evo" INTEGER, 
	"baseExp" INTEGER);

CREATE TABLE ATTACK (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT, 
	"name" TEXT, 
	"type" INTEGER, 
	"max_pp" INTEGER, 
	"active" INTEGER, 
	"ifPass" INTEGER, 
	"power" INTEGER, 
	"probability" INTEGER);

CREATE TABLE CAPTURED (
	"id_Captured" INTEGER PRIMARY KEY AUTOINCREMENT, 
	"id_Animature" INTEGER, 
	"save" INTEGER, 
	"nickname" TEXT, 
	"sex" INTEGER, 
	"status" INTEGER, 
	"capturedTime" INTEGER, 
	"attack1" INTEGER, 
	"attack1_pp" INTEGER, 
	"attack2" INTEGER, 
	"attack2_pp" INTEGER, 
	"attack3" INTEGER, 
	"attack3_pp" INTEGER, 
	"attack4" INTEGER, 
	"attack4_pp" INTEGER, 
	"health" INTEGER, 
	"level" INTEGER, 
	"cur_exp" INTEGER, 
	"exp" INTEGER, 
	"box" INTEGER, 
	FOREIGN KEY(id_Animature) REFERENCES ANIMATURE(id), 
	FOREIGN KEY(save) REFERENCES SAVE(id), 
	FOREIGN KEY(attack1) REFERENCES ATTACK(id), 
	FOREIGN KEY(attack2) REFERENCES ATTACK(id), 
	FOREIGN KEY(attack3) REFERENCES ATTACK(id), 
	FOREIGN KEY(attack4) REFERENCES ATTACK(id));

CREATE TABLE ITEM (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT,
	"name" TEXT,
	"type" NUMBER,	
	"description" TEXT);

CREATE TABLE ENEMY (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT, 
	"map" INTEGER, 
	"coord_x" INTEGER, 
	"coord_y" INTEGER, 
	"money" INTEGER,
	"an1" INTEGER, 
	"an2" INTEGER, 
	"an3" INTEGER, 
	"an4" INTEGER, 
	"an5" INTEGER, 
	"an6" INTEGER, 
	FOREIGN KEY(an1) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an2) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an3) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an4) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an5) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an6) REFERENCES CAPTURED(id_Captured));

CREATE TABLE SAVE (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT, 
	"character" INTEGER, 
	"stage" INTEGER, 
	"last_played" DATE, 
	"started" DATE, 
	"total_time" INTEGER, 
	"steps" INTEGER, 
	"an1" INTEGER, 
	"an2" INTEGER, 
	"an3" INTEGER, 
	"an4" INTEGER, 
	"an5" INTEGER, 
	"an6" INTEGER, 
	"map" INTEGER, 
	"coord_x" INTEGER, 
	"coord_y" INTEGER, 
	"neighbour" INTEGER, 
	"first_an" INTEGER, 
	"orientation" INTEGER, 
	"last_HealingMap" INTEGER, 
	"last_HealingX" INTEGER, 
	"last_HealingY" INTEGER, 
	"medals" INTEGER, 
	"money" INTEGER, 
	FOREIGN KEY(an1) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an2) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an3) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an4) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an5) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(an6) REFERENCES CAPTURED(id_Captured), 
	FOREIGN KEY(first_an) REFERENCES CAPTURED(id_Captured));

CREATE TABLE USER (
	"id" INTEGER PRIMARY KEY AUTOINCREMENT, 
	"email" TEXT, 
	"password" TEXT, 
	"username" TEXT, 
	"is_current" INTEGER, 
	"save" INTEGER, 
	FOREIGN KEY(save) REFERENCES SAVE(id));


CREATE TABLE BAG (
	"save" NUMBER, 
	"item" NUMBER, 
	"quantity" NUMBER, 
	FOREIGN KEY(save) REFERENCES SAVE(id), 
	FOREIGN KEY(item) REFERENCES OBJECT(id),
	PRIMARY KEY (save, object));

CREATE TABLE VIEWEDANIMATURE (
	"id_Save" INTEGER REFERENCES SAVE(id),
	"id_Animature" INTEGER REFERENCES ANIMATURE(id),
	PRIMARY KEY (id_Save, id_Animature));

CREATE TABLE ANIMATUREATTACK (
	"id_Animature" INTEGER REFERENCES ANIMATURE(id),
	"id_Attack" INTEGER REFERENCES ATTACK(id),
	"level" INTEGER, 
	PRIMARY KEY (id_Animature, id_Attack));

CREATE TABLE FIGHTS (
	"save" INTEGER PRIMARY KEY, 
	"enemy" INTEGER, 
	FOREIGN KEY(save) REFERENCES SAVE(id), 
	FOREIGN KEY(enemy) REFERENCES ENEMY(id));

INSERT INTO ANIMATURE VALUES (1, 'BULBASUR', 0.7, 6.9, 3, 7, 100, 120, 100, 120, 100, 80, 16, 150);
INSERT INTO ANIMATURE VALUES (2, 'IVYSAUR', 1, 13, 3, 7, 100, 120, 100, 120, 100, 80, 36, 500);
INSERT INTO ANIMATURE VALUES (3, 'VENUSAUR', 2, 100, 3, 7, 100, 120, 100, 120, 100, 80, -1, 1200);

INSERT INTO ANIMATURE VALUES (4, 'CHARMANDER', 0.6, 8.5, 1, -1, 100, 120, 100, 120, 100, 80, 16, 150);
INSERT INTO ANIMATURE VALUES (5, 'CHARMELEON', 1.1, 19, 1, -1, 100, 120, 100, 120, 100, 80, 36, 500);
INSERT INTO ANIMATURE VALUES (6, 'CHARIZARD', 1.7, 90.5, 1, 9, 100, 120, 100, 120, 100, 80, -1, 1200);

INSERT INTO ANIMATURE VALUES (7, 'SQUIRTLE', 0.5, 9, 2, -1, 100, 120, 100, 120, 100, 80, 16, 150);
INSERT INTO ANIMATURE VALUES (8, 'WARTORTLE', 1, 22.5, 2, -1, 100, 120, 100, 120, 100, 80, 36, 500);
INSERT INTO ANIMATURE VALUES (9, 'BLASTOISE', 1.6, 85.5, 2, -1, 100, 120, 100, 120, 100, 80, -1, 1200);

INSERT INTO ANIMATURE VALUES (10, 'CATERPIE', 0.3, 2.9, 11, -1, 100, 120, 100, 120, 100, 80, 8, 150);
INSERT INTO ANIMATURE VALUES (11, 'METAPOD', 0.7, 9.9, 11, -1, 100, 120, 100, 120, 100, 80, 18, 500);
INSERT INTO ANIMATURE VALUES (12, 'BUTTERFREE', 1.1, 32, 11, 9, 100, 120, 100, 120, 100, 80, -1, 1200);

INSERT INTO ANIMATURE VALUES (13, 'WEEDLE', 0.3, 3.2, 11, 7, 100, 120, 100, 120, 100, 80, 8, 150);
INSERT INTO ANIMATURE VALUES (14, 'KAKUNA', 0.6, 10, 11, 7, 100, 120, 100, 120, 100, 80, 18, 500);
INSERT INTO ANIMATURE VALUES (15, 'BEEDRILL', 1, 29.5, 11, 7, 100, 120, 100, 120, 100, 80, -1, 1200);

INSERT INTO ANIMATURE VALUES (16, 'PIDGEY', 0.3, 3.2, 0, 9, 100, 120, 100, 120, 100, 80, 16, 150);
INSERT INTO ANIMATURE VALUES (17, 'PIDGEOTTO', 0.6, 10, 0, 9, 100, 120, 100, 120, 100, 80, 36, 500);
INSERT INTO ANIMATURE VALUES (18, 'PIDGEOT', 1, 29.5, 0, 9, 100, 120, 100, 120, 100, 80, -1, 1200);

INSERT INTO ANIMATURE VALUES (19, 'RATTATA', 0.3, 3.5, 0, -1, 100, 120, 100, 120, 100, 80, 12, 150);
INSERT INTO ANIMATURE VALUES (20, 'RATICATE', 0.7, 18.5, 0, -1, 100, 120, 100, 120, 100, 80, 25, 500);
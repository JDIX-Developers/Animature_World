# Base de datos #

## Tabla User ##
(id, email, password, username, is_current, *save*)

## Tabla Animature ##
(id, name, height, weight, type, type2, speed, defense, agility, strenght, precission, health, level_evo)

## Tabla Save ##
(id, name, sex, character, stage, last_played, started, total_time, steps, *an1*, *an2*, *an3*, *an4*, *an5*, *an6*, *map*, coord_x, coord_y, neighbour, *1st_an*, orientation, last_Healing, medals, money)

## Tabla Captured ##
(id, *animature*, *save*, nickname, sex, status, capturedTime, *attack1*, attack1_pp, *attack2*, attack2_pp, *attack3*, attack3_pp, *attack4*, attack4_pp, health, level, cur_exp, exp, box)

## Tabla Bag ##
(*save*, *object*, quantity)

## Tabla Objects ##
(id, type)

## Tabla Enemies ##
(id, map, coord_x, coord_y, *an1*, *an2*, *an3*, *an4*, *an5*, *an6*)

## Tabla Fights ##
(*save*, *enemy*)

## Tabla Attacks ##
(id, name, type, max_pp, active, ifPass, power, probability)
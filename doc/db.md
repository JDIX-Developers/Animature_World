# Base de datos #

## Tabla user ##
(id, email, password, username)

## Tabla Animature ##
(id, name, height, weight, type, velocity, defense, agility, strenght, health, level_evo)

## Tabla Save ##
(id, *user*, stage, last_played, started, total_time, steps, *an1*, *an2*, *an3*, *an4*, *an5*, *an6*, *map*, coord_x, coord_y, neighbour, *1st_an*, orientation)

## Tabla Captured ##
(id, *animature*, *save*, nickname, sex, *attack1*, attack1_pp, *attack2*, attack2_pp, *attack3*, attack3_pp, *attack4*, attack4_pp, health, exp, box)

## Tabla Bag ##
(*object*, *save*, quantity)

## Tabla Objects ##
(id, type)

## Tabla Enemies ##
(id, map, coord_x, coord_y, *an1*, *an2*, *an3*, *an4*, *an5*, *an6*)

## Tabla Fights ##
(enemy, save)

## Tabla Attacks ##
(id, name, type, max_pp, precission)
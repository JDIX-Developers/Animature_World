# Base de datos #

## Tabla USER	 ##
(id, email, password, username, is_current, remember)

## Tabla ANIMATURE ##
(id, animature, *save*, nickname, status, attack1, attack1_pp, attack2, attack2_pp, attack3, attack3_pp, attack4, attack4_pp, health, level, cur_exp)

## Tabla SAVE ##
(id, *user*, name, sex, stage, last_played, start_date, total_time, steps, *an1*, *an2*, *an3*, *an4*, *an5*, *an6*, map, coord_x, coord_y, neighbor, first_an, orientation, last_healing_map, last_healing_x, last_healing_y, medals, money)

## Tabla BAG ##
(*save*, item, quantity)

## Tabla ENEMY ##
(id, map, coord_x, coord_y, money, *an1*, *an2*, *an3*, *an4*, *an5*, *an6*)

## Tabla FIGHT ##
(*save*, *enemy*)

## Tabla VIEWED ##
(*save*, *animature*)
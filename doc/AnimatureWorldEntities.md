-- CLASE ANIMATURE --

int		id_Animature		Identificador animature.
String	nameAnimature		Nombre del animature.
double	heightAnimature		Altura del animature.
double	weightAnimature		Peso del animature.
int		typeAnimature[2]	Tipo/s del animature. (Veneno, planta, agua, fuego,...)
String	information			Descripción del animature. (Para visualizarla en el Animax)
int		cualities[5]		Cualidades del animature. (0-Velocidad, 1-Defensa. 2-Agilidad, 3-Fuerza, 4-Precisión)
int		healthAnimature		Vida del Animature.
int		levelEvoAnimature	Nivel en el que evoluciona el animature. (0 si no evoluciona)
int		expericeBase		Experiencia base del animature. (Para obtener la de los siguientes niveles)
int		fightType[17]		Muestra contra que tipo de animatures es fuerte con un 1, debil con un 2 y ni fuerte ni debil 0.
boolean isViewed			Es igual a true si el Animature a sido visto. (Sirve para el Animax para poder ver los datos del Animature)


-- CLASE CAPTURED extends Animature --

int		id_AnimatureCaptured	Identificador del animature capturado. (No se utiliza el de ANIMATURE porque puede atrapar varios iguales)
String	nicknameAnimature		Nombre / Apodo del animature caputado.
int		levelAnimature			Nivel del animature capturado.
int 	sexAnimature			Sexo del animature capturado. (0-Macho, 1-Hembra)
int		statusAnimature			Estado del animature capturado. (0-Normal, 1-Paralizado, 2-Quemado, 3-Envenenado, 4-Dormido, 5-Congelado)
Attack	attacksAnimature[4]		Ataques del animature capturado.
int 	currentAttacksPP [4]	Veces que los ataques han sido utilizados.
int		currentExpAnimature		Experiencia actual del animature capturado.
int		currentHealthAnimature	Vida actual del animature.
boolean	box						Es igual a true si el Animature captured esta entre los 6 elegidos del jugador.


-- CLASE ATTACK --

int		id_Attack			Identificador del ataque.
String	nameAttack			Nombre del ataque.
int		typeAttack			Tipo del ataque. (Veneno, planta, agua, fuego,...)
int 	maxPPAttack			Número máximo de veces que puede ser utilizado el ataque.
int		powerAttack			Poder del ataque.
int		probabilityAttack	Probabilidad de que el ataque tenga exito.
boolean passiveAttack		Es true si el ataque no quita vida, sino que, aumenta una de las cualidades de tu animature.


-- CLASE ITEM --

int 	id_Item				Identificador del ítem.
String 	nameItem			Nombre del ítem.
String	description			Descripción del ítem.
int 	quantituy			Número de ítems.

-- CLASE PLAYER --

int 	id_Player				Identificador del jugador.
String	namePlayer				Nombre del jugador.
int 	sexPlayer				Sexo del jugador. (0 - Chico, 1 - Chica)
String	playerEnemyName			Nombre del enemigo del jugador.
int 	stage					Etapa del juego.
long 	startedTime				Tiempo de inicio del juego en milisegundos.
long	lastPlayedTime			Ultima vez que se ha jugado en milisegundos.
long	stepsPlayer				Número de pasos que ha dado el jugador.
Animature activeAnimatures[6]	Animatures que el jugador lleva encima.
int 	coord_X					Número de la coordenada x del jugador.
int 	coord_Y					Número de la coordenada y del jugador.
int 	orientation				Orientación del jugador. (1-Arriba, 2-Derecha, 3-Abajo, 5-Izquierda)
int 	medals					Número de medallas que ha conseguido el jugador.
long 	money					Dinero del jugador.
Vector <Item> items				Ítems del jugador.


---> PROBLEMAS <---

¿Como recordamos el último centro de curación donde se ha curado para que cuando el jugador sea derrotado vuelva a aparecer en ese mismo lugar?

¿Como recordamos el orden de los Animatures que lleva el jugador encima?
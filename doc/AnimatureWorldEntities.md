-- CLASE ANIMATURE --

int		id_Animature		Identificador animature.
String	nameAnimature		Nombre del animature.
double	heightAnimature		Altura del animature.
double	weightAnimature		Peso del animature.
int		typeAnimature[2]	Tipo/s del animature. (Veneno, planta, agua, fuego,...)
int		cualities[5]			Cualidades del animature. (0-Velocidad, 1-Defensa. 2-Agilidad, 3-Fuerza, 4-Precisión)
int		healthAnimature		Vida del Animature.
int		levelEvoAnimature	Nivel en el que evoluciona el animature. (0 si no evoluciona)
int		expericeBase		Experiencia base del animature. (Para obtener la de los siguientes niveles)
int		fightType[17]		Muestra contra que tipo de animatures es fuerte con un 1, debil con un 2 y ni fuerte ni debil 0.


-- CLASE CAPTURED extends Animature --

int		id_AnimatureCaptured	Identificador del animature capturado. (No se utiliza el de ANIMATURE porque puede atrapar varios iguales)
String	nicknameAnimature		Nombre / Apodo del animature caputado.
int		levelAnimature			Nivel del animature capturado.
int 	sexAnimature			Sexo del animature capturado. (0-Macho, 1-Hembra)
int		statusAnimature			Estado del animature capturado. (0-Normal, 1-Paralizado, 2-Quemado, 3-Envenenado, 4-Dormido, 5-Congelado)
Attack	attacksAnimature[4]		Ataques del animature capturado.
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
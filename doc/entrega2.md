# Animature World #

El programa será una implementación de un clon del aclamado Pokemon para Android. La idea es implementar la jugabilidad lo más completa posible, teniendo en cuenta las limitaciones, principalmente de tiempo que tenemos. La implementación se llevará a cabo para la plataforma Android.

En principio, por falta de tiempo, nuestro principal objetivo será crear una demo jugable, en la que el usuario comenzará en un pueblo, recibirá un Animature, y podrá luchar contra la IA. También deberá viajar al pueblo continuo y acceder a centros Animature. Podrá luchar contra animatures salvajes.

Todo ello estará rodeado de una GUI que no será igual a la de Pokemon, ya que se adaptará a las posibilidades táctiles de los smartphones. El proyecto tendrá un servidor central en el que se controlarán las cuentas de usuario, y se guardarán las partidas, como copia de seguridad. Se podrá guardar una partida por usuario/contraseña.

El juego en el móvil usará una base de datos, primero para almacenar las credenciales del usuario, y que así no las tenga que introducirlas cada vez, y también se guardarán en ella las partidas guardadas. Por lo tanto, el servidor será un mero servicio de backup, para ocasiones como por ejemplo el cambiar de móvil.

El sistema de mapas se generará con un creador de mapas que incluimos en el proyecto, cuanto antes para permitir el desarrollo de los mismos de una manera gráfica, para simplificar todo lo posible ese aspecto. Estará programado en Java.

En cuanto al lenguaje del juego y del creador de mapas, hemos creado un programa que crea lenguajes, con ficheros .lang. De momento este progrma solo esta en uso en el creador de mapas pero en un futuro se utilizara tambien en el Animature World, con el obejtivo de poder internacionalizarlo.

La documentación de la API y las especificaciones de los mapas etc. estará disponible en el repositorio.

En un futuro tenemos la intencion de cambiar las imagenes originales del Pokemon, por unas creadas por nosotros sin copyright.

## Requisitos ##

* El jugador podrá registrarse en la aplicación.
* El jugador podrá loguearse en la aplicación.
* El jugador podrá crear una nueva partida.
* El jugador podrá guardar la partida en curso.
* Los desarrolladores de mapas tendrán una aplicación simple para su creación.
* El jugador deberá ser capaz de controlar el personaje en el juego.
* El personaje podrá entrar en algunos edificios.
* El personaje podrá luchar contra enemigos.
* El personaje no podrá visitar más de dos pueblos.
* Los animatures podrán recibir experiencia y subir de nivel, sin sobrepasar el nivel 100.
* Cada nivel aumentará las cualidades del animature.
* No se podrá jugar en modo multijugador en esta versión.
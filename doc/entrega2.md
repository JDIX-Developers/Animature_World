# Animature World #

El programa será una implementación de un clon del aclamado Pokemon para Android, evitando en todo momento copyrights, para en un futuro poder venderlo. La idea es implementar la jugabilidad lo más completa posible, teniendo en cuenta las limitaciones, principalmente de tiempo que tenemos. La implementación se llevará a cabo para la plataforma Android.

En principio, por falta de tiempo, nuestro principal objetivo será crear una demo jugable, en la que el usuario comenzará en un pueblo, recibirá un Animature, y podrá luchar contra la IA. También deberá viajar al pueblo continuo, donde podrá comprar objetos en la tienda, y hacer gestiones en el centro Animature. Podrá luchar contra animatures salvajes, y capturarlos.

Todo ello estará rodeado de una GUI que no será igual a la de Pokemon, ya que se adaptará a las posibilidades táctiles de los smartphones. El proyecto tendrá un servidor central en el que se controlarán las cuentas de usuario, y se guardarán las partidas, como copia de seguridad. Se podrá guardar una partida por usuario/contraseña.

El juego en el móvil usará una base de datos, primero para almacenar las credenciales del usuario, y que así no las tenga que introducirlas cada vez, y también se guardarán en ella las partidas guardadas. Por lo tanto, el servidor será un mero servicio de backup, para ocasiones como por ejemplo el cambiar de móvil.

El sistema de mapas se generará con un creador de mapas que tenemos intención de crear, cuanto antes para permitir el desarrollo de los mismos de una manera gráfica, para simplificar todo lo posible ese aspecto. Estará programado en Java.

La documentación de la API y las especificaciones de los mapas etc. estará disponible en el repositorio.

## Requisitos ##

* El jugador podrá registrarse en la aplicación.
* El jugador podrá loguearse en la aplicación.
* El jugador podrá cambiar la configuración de la aplicación.
* El jugador podrá crear nuevas partidas.
* El jugador podrá guardar la partida en curso.
* Las partidas se guardarán también en el servidor central.
* Los desarrolladores de mapas tendrán una aplicación simple para su creación.
* El jugador deberá ser capaz de controlar el personaje en el juego.
* El personaje podrá entrar en algunos edificios.
* El personaje podrá luchar contra enemigos.
* En los arbustos aparecerán animatures salvajes que podrá capturar.
* El personaje podrá curar a sus animatures en un centro animature.
* Se podrán comprar cosas en las tiendas animature.
* Se podrá hablar con otras personas con IA.
* El personaje no podrá visitar más de dos pueblos.
* Los animatures podrán recibir experiencia y subir de nivel, sin sobrepasar el nivel 100.
* Cada nivel aumentará la efectividad del animature.
* No se podrá jugar en modo multijugador en esta versión.

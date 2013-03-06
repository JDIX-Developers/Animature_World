# API del servidor #

Esta API solo servirá para poder ser usada por Animature World. Cualquier uso de la API por otra aplicación no está permitido. El funcionamiento de la API se llevará a cabo de la siguiente manera:

## Funcionamiento de la conexión ##

Para conectarse con el servidor se usará la clase Connection, del paquete *utils*.
Es una clase con patrón Singleton, así que se deberá hacer uso del método *getInstance()* para usarla.

En toda conexión se deberá incluir un action, que deberá ser, por el momento o *login* o *register*. También está disponible *test* como prueba para comprobar la conexión, pero será retirado en cuanto cumpla su función.

Se podrán añadir datos con el método *addData(String index, String value)*. En cada action se deberá incluir una serie de información concreta.

El sistema devolverá un objeto JSON, con el que se podrán extraer todas las propiedades necesarias. La consulta se ejecutará con el método *execute()*

### Ejemplo de uso ###

```java
Connection c = Connection.getInstance();

c.setAction("login");
c.addData("email", "test@razican.com");
c.addData("pass", StringUtils.sha1("test"+"--Animature"));

JSONObject json = c.execute();
```

## Modos de la API en el servidor ##

En el servidor, la API tendrá varios action, que pasaré a describir a continuación:

### Action: login ###

Se deberán especificar 3 datos: method, email y pass. El method será *manual* para todos los logins. *auto* está reservado para un login automático para aumentar la seguridad de la conexión, ya que usa tokens regenerables.
El *email* será el email usado por el usuario al intentar entrar. Deberá estar comprobado y no deberá ser vacío.
La *pass* será la contraseña usada por el usuario, no podrá ser un string vacío, y se enviará al servidor encriptada en Sha1 incluyendo tras el string de la contraseña el siguiente string: *--Animature*. Esto aumentará la seguridad.

El servidor devolverá 3 valores: email, pass, token. El *email* será un booleano (TRUE o FALSE) que indicará si el email es válido o no. El *pass* será un booleano que indicará, una vez comprobado el email, si la contraseña es correcta o no.
En el caso en el que ambos sean TRUE, es decir, que el email y la contraseña son válidos, se incluirá el token, que setá un string hexadecimal de 32 caracteres que controlará la conexión. No es necesario controlarlo, ya que la propia conexión gestionará el token.

Lo que sí es importante es que una vez hecho el login, se le incluya a la conexión los datos del login, para lo que se usará el método estático *setLogin()*:

```java
Connection.setLogin("test@razican.com", StringUtils.sha1("test"+"--Animature"));
```

### Action: register ###

Se deberán especificar 3 datos: user, email, pass. El primero será el usuario que el jugador tendrá en Internet y en el juego. No podrá estar vacío. El segundo será un email comprobado, y el tercero será una contraseña, que previamente deberá haber sido comprobada para evitar errores (doble introducción). Esta última se transmitirá en sha1, como en el login.

El servidor devolverá 3 valores: user, email, y token. Respectivamente, un booleano indicando la validez del usuario, otro indicando la del email, y un string para controlar la conexión.

Un usuario recién registrado deberá ir al juego directamente, y por ende, se deberán incluir en Connection los datos de acceso.
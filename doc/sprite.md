# Especificación de los Sprites v1.1#

Los sprites serán el método para pintar cuadrados en los mapas. Para gestionarlos de una manera fácil se va a seguir esta especificación.

## Sprites de producción ##

Para el creador de mapas, o para el juego, se usarán unos sprites que contendrán 2 archivos. Un *.png* con la imagen del sprite y un archivo *.spr* con los datos del sprite. Ambos archivos deberán tener el mismo nombre, y en el caso del creador de mapas deberán estar localizados en el mismo directorio. En el caso del juego, el *.spr* estará en *res/raw* y el *.png* en *res/drawable*. En el creador de mapas necesitaremos un archivo *.dspr* con la jerarquía del sprite.

El *.spr* será un archivo binario, y tendrá el siguiente formato: Los dos primeros bytes formarán el short que indicará el tamaño de los cuadrados, en pixels. A partir de ahí, cada 3 bytes serán un cuadrado. El primer byte será la coordenada X del cuadrado, el segundo la coordenada Y y el tercero el tipo de cuadrado. Se reservan los *0xFF* para otras cuestiones.

El *.dspr* será un archivo de objetos. No se ha complicado el formato de archivo, ya que solo se van a usar para el desarrollo, y no para el juego, y por lo tanto no es necesario que sean excesivamente eficientes. Contendrán un único objeto, *un HashMap*, cuya clave será un *String* que contendrá el path del JTree del creador de mapas y el valor será un *Map.Entry<Byte, Byte>* que contendrá las coordenadas X e Y del cuadrado en el sprite.

El creador de mapas solo funcionará con sprites de 128x128 pixels por cuadrado.

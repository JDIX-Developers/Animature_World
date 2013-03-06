# Especificación de los MapFiles #

Un MapFile es un archivo con extensión .map que contiene una porción de universo del juego. Será un archivo binario, que estará codificado según esta especificación. Todo el archivo se dividirá en porciones de 2 bytes. Cada 2 bytes erá un dato.

El primer dato indicará el ancho y el alto del mapa, el ancho el primer byte y el alto el segundo. A partir de entonces, cada dato será un cuadrado del sprite, con coordenadas XXYY.

Hay dos tipos de datos especiales, que habilitan la compresión del mapa. Los datos que tengan el primer o el último byte *0xFF* serán de este tipo.
Los que tengan el segundo byte en *0xFF* serán las repeticiones en la coordenada X. Así que *0x07FF* significará que el dato anterior se repetirá 7 veces en la coordenada X contando el cuadrado actual. El máximo será 255. El traductor no fallará con *0x00FF*, pero a ser posible deberá evitarse. Tampoco es aconsejable usar *0x01FF*, ya que es una notación que puede inducir a error, en su lugar se repetirá el dato de la izquierda.
Por otro lado, los que tienen el primer byte en *0xFF* se repetirán en la coordenada Y, y se aplican las mismas normas.

## Compresión: ##

A la hora de comprimir, se debe premiar el menor tamaño posible, ya que mejorará la carga del mapa. Para ello, tras seguir las recomendaciones anteriores, se deben seguir otras para los casos en los que un dato se repite tanto en dirección X como en Y formando rectángulos. En esos casos, se usará  como repetición la coordenada en la que se repita más veces. En el caso en el que las repeticiones sean iguales, se usarán las repeticiones en X.

En el caso en el que los datos se repitan en X y en Y pero sin formar un rectángulo, se hará la repetición de la coordenada que en total tenga más datos. Si ambas tienen la misma cantidad de datos, se usarán las repeticiones en X.
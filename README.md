## Simulación de Batalla de Superhéroes

Cada superhéroe tiene ciertos poderes y atributos como fuerza, velocidad y vida_hp.
Imagina que tienes varios superhéroes y villanos en un universo de cómic. Cada superhéro tiene atributos como:
- `nombre`
- `fuerza`
- `velocidad`
- `vida_hp`
En un método tendrá el ataque especial.

### Los superhéroes podrán
1. `Atacar`: Reducirá la vida_hp del oponente.
2. `Defiende`: Se protejerá el 50% del daño recibido.
2. `Aumentar sus poderes`: Sobrecargando el método de ataque.
3. `Recuperarse`: Esto aumenta su vida_hp
4. `Mostrar estadísticas actuales`

### Clases involucradas en el juego
`personaje` --> Base para Héroes y Villanos.
`superHero` --> Hereda *personaje*, puede realizar ataques especiales.
`villano` --> Hereda *personaje*, puede hacer trampa y duplicar su ataque.
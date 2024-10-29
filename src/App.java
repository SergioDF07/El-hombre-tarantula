import java.util.Random;
import java.util.Scanner;

// Clase base del personaje
class Personaje {
    String nombre;
    int fuerza;
    int velocidad;
    int vida_hp;

    public Personaje(String nombre, int fuerza, int velocidad, int vida_hp) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.vida_hp = vida_hp;
    }

    public boolean estaVivo() {
        return this.vida_hp > 0;
    }

    public void recibirDaño(int daño) {
        this.vida_hp -= daño;
        if (this.vida_hp < 0) {
            this.vida_hp = 0;
        }
    }

    public void mostrarEstadisticas() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Vida: " + vida_hp);
    }
}

// Clase SuperHero (Spider-Man)
class SuperHero extends Personaje {

    public SuperHero(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp);
    }

    public void ataqueEspecial(Personaje villano) {
        System.out.println(nombre + " usa su ataque especial: ¡Lanzar Telaraña!");
        int daño = fuerza + 15; // El ataque especial hace más daño
        villano.recibirDaño(daño);
        System.out.println(villano.nombre + " ha recibido " + daño + " de daño.");
    }

    public void atacar(Personaje villano) {
        System.out.println(nombre + " ataca a " + villano.nombre);
        villano.recibirDaño(fuerza);
        System.out.println(villano.nombre + " ha recibido " + fuerza + " de daño.");
    }

    public void defender(int daño) {
        System.out.println(nombre + " se defiende, reduciendo el daño a la mitad.");
        this.recibirDaño(daño / 2);
    }

    public void recuperarse() {
        System.out.println(nombre + " se recupera y aumenta su vida.");
        this.vida_hp += 20;
        System.out.println("La vida de " + nombre + " ahora es: " + vida_hp);
    }
}

// Clase Villano
class Villano extends Personaje {
    Random random = new Random();

    public Villano(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp);
    }

    public void ataqueEspecial(Personaje heroe) {
        int habilidad = random.nextInt(2);
        if (habilidad == 0) {
            System.out.println(nombre + " usa su ataque especial: ¡Ataque duplicado!");
            int daño = fuerza * 2;
            heroe.recibirDaño(daño);
            System.out.println(heroe.nombre + " ha recibido " + daño + " de daño.");
        } else {
            System.out.println(nombre + " usa su ataque especial: ¡Trampa sucia!");
            int daño = fuerza + 10;
            heroe.recibirDaño(daño);
            System.out.println(heroe.nombre + " ha recibido " + daño + " de daño.");
        }
    }

    public void atacar(Personaje heroe) {
        System.out.println(nombre + " ataca a " + heroe.nombre);
        heroe.recibirDaño(fuerza);
        System.out.println(heroe.nombre + " ha recibido " + fuerza + " de daño.");
    }
}

// Clase principal del juego
public class App {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            
            // Crear el héroe (Spider-Man)
            SuperHero spiderman = new SuperHero("Spider-Man", 20, 15, 100);
            
            // Crear villanos (uno aleatorio de los 7 Siniestros)
            Villano[] villanos = {
                new Villano("Duende Verde", 38, 30, 45),
                new Villano("Doctor Octopus", 20, 12, 85),
                new Villano("Electro", 22, 40, 75),
                new Villano("Misterio", 15, 20, 70),
                new Villano("Sandman", 25, 9, 90),
                new Villano("Rhino", 30, 8, 100),
                new Villano("Vulture", 17, 19, 80)
            };
            
            // Seleccionar un villano aleatorio
            Villano villanoActual = villanos[random.nextInt(villanos.length)];
            System.out.println("¡Spider-Man se enfrentará a " + villanoActual.nombre + "!");
            
            // Bucle de juego
            while (spiderman.estaVivo() && villanoActual.estaVivo()) {
                System.out.println("\n== Estado Actual ==");
                spiderman.mostrarEstadisticas();
                villanoActual.mostrarEstadisticas();
                
                System.out.println("\nElige una acción:");
                System.out.println("1. Atacar");
                System.out.println("2. Ataque especial (Lanzar Telaraña)");
                System.out.println("3. Defenderse");
                System.out.println("4. Recuperarse");
                int eleccion = scanner.nextInt();
                
                // Turnos de acción basados en velocidad
                if (spiderman.velocidad > villanoActual.velocidad) {
                    // Spider-Man actúa primero
                    ejecutarAccionHeroe(spiderman, villanoActual, eleccion);
                    if (villanoActual.estaVivo()) {
                        ejecutarAccionVillano(villanoActual, spiderman);
                    }
                } else {
                    // El villano actúa primero
                    ejecutarAccionVillano(villanoActual, spiderman);
                    if (spiderman.estaVivo()) {
                        ejecutarAccionHeroe(spiderman, villanoActual, eleccion);
                    }
                }
            }
            
            if (spiderman.estaVivo()) {
                System.out.println("\n¡Spider-Man ha derrotado a " + villanoActual.nombre + "!");
            } else {
                System.out.println("\n" + villanoActual.nombre + " ha derrotado a Spider-Man.");
            }
        }
    }

    public static void ejecutarAccionHeroe(SuperHero heroe, Villano villano, int accion) {
        switch (accion) {
            case 1 -> heroe.atacar(villano);
            case 2 -> heroe.ataqueEspecial(villano);
            case 3 -> heroe.defender(villano.fuerza);
            case 4 -> heroe.recuperarse();
            default -> System.out.println("Acción no válida.");
        }
    }

    public static void ejecutarAccionVillano(Villano villano, SuperHero heroe) {
        System.out.println("\nEs el turno de " + villano.nombre);
        int accion = new Random().nextInt(2);
        if (accion == 0) {
            villano.atacar(heroe);
        } else {
            villano.ataqueEspecial(heroe);
        }
    }
}

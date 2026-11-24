import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class Pelicula {
    String titulo;
    double duracion, precio;
    int vendidas;

    Pelicula(String titulo, double duracion, double precio) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.precio = precio;
    }

    double vender(int cant) {
        this.vendidas += cant;
        return cant * this.precio;
    }

    double recaudacion() {
        return this.vendidas * this.precio;
    }
}

class Sala {
    int numero;
    Pelicula pelicula;

    Sala(int numero) {
        this.numero = numero;
    }
}

class Cine {
    ArrayList<Sala> salas = new ArrayList<>();

    Cine(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            salas.add(new Sala(i));
        }
    }

    boolean hayCargadas() {
        for (Sala s : salas) {
            if (s.pelicula != null) return true;
        }
        return false;
    }

    void mostrar() {
        for (Sala s : salas) {
            if (s.pelicula != null) {
                System.out.println("Sala " + s.numero + ": " + s.pelicula.titulo
                        + " | " + s.pelicula.duracion + "m | $" + s.pelicula.precio
                        + " | Vendidas: " + s.pelicula.vendidas);
            } else {
                System.out.println("Sala " + s.numero + ": vacia");
            }
        }
    }

    boolean cambiarPrecio(int nro, double nuevoPrecio) {
        if (nuevoPrecio <= 0) return false;
        for (Sala s : salas) {
            if (s.numero == nro && s.pelicula != null) {
                s.pelicula.precio = nuevoPrecio;
                return true;
            }
        }
        return false;
    }

    Double venderPorTitulo(String titulo, int cant) {
        if (cant <= 0) return null;
        for (Sala s : salas) {
            if (s.pelicula != null && s.pelicula.titulo.equalsIgnoreCase(titulo)) {
                return s.pelicula.vender(cant);
            }
        }
        return null;
    }

    void recaudacionTotal() {
        double total = 0;
        for (Sala s : salas) {
            if (s.pelicula != null) {
                double rec = s.pelicula.recaudacion();
                total += rec;
                System.out.println("Sala " + s.numero + " (" + s.pelicula.titulo + "): $" + rec);
            }
        }
        System.out.println("Total cine: $" + total);
    }

    void duraciones() {
        for (Sala s : salas) {
            if (s.pelicula != null) {
                String tipo = s.pelicula.duracion > 120 ? "LARGA" : "NO LARGA";
                System.out.println("Sala " + s.numero + " - " + s.pelicula.titulo + ": " + tipo);
            }
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        Cine cine = new Cine(5);
        int op;

        do {
            System.out.println("\n1.Registrar 2.Mostrar 3.Precio 4.Vender 5.Recaudacion 6.Duracion 0.Salir");
            op = sc.nextInt();
            sc.nextLine();

            if (op >= 2 && op <= 6 && !cine.hayCargadas()) {
                System.out.println("Primero registra peliculas (opcion 1).");
                continue;
            }

            switch (op) {
                case 1:
                    for (Sala s : cine.salas) {
                        System.out.print("Sala " + s.numero + " titulo: ");
                        String t = sc.nextLine();
                        System.out.print("Duracion: ");
                        double d = sc.nextDouble();
                        System.out.print("Precio: ");
                        double p = sc.nextDouble();
                        sc.nextLine();
                        s.pelicula = new Pelicula(t, d, p);
                    }
                    break;
                case 2: cine.mostrar(); break;
                case 3:
                    System.out.print("Sala (1-" + cine.salas.size() + "): ");
                    int nro = sc.nextInt();
                    System.out.print("Nuevo precio: ");
                    double np = sc.nextDouble();
                    sc.nextLine();
                    System.out.println(cine.cambiarPrecio(nro, np) ? "Actualizado" : "Error");
                    break;
                case 4:
                    System.out.print("Titulo: ");
                    String nom = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int c = sc.nextInt();
                    sc.nextLine();
                    Double tot = cine.venderPorTitulo(nom, c);
                    System.out.println(tot != null ? "Total: $" + tot : "No encontrado / cantidad invalida");
                    break;
                case 5: cine.recaudacionTotal(); break;
                case 6: cine.duraciones(); break;
                case 0: System.out.println("Chau!"); break;
                default: System.out.println("Opcion invalida");
            }
        } while (op != 0);
        sc.close();
    }
}
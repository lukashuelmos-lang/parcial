import java.util.Scanner;
import java.util.ArrayList;
import java.util.locale;

class Pelicula{
    String titulo;
    double duracion;
    double precio;
    int cantidad;

    Pelicula(String titulo, double duracion, double precio){
        this.titulo = titulo;
        this.duracion = duracion;
        this.precio = precio;
        this.cantidad = 0;
    }

    double vendidas(int cant){
        this.cantidad += cant;
        return this.precio * cant;
    }

    double recaudacion(){
        return  this.cantidad * this.precio;
    }

}   

class Sala{
    int numero;
    Pelicula pelicula;

    Sala(int numero){
        this.numero = numero;
    }

}

class Cine{
    ArrayList<Sala> salas = new ArrayList <> ();

    Cine(int cant){
        for(int i = 1; i <= cant; i++){
            salas.add(new Sala(i));
        }
    }


boolean hayCargadas(){
    for(Sala s : salas) {
        if(s.pelicula != null){
            return true;
        }
    }
        return false;
}

void mostrar(){
    for(Sala s : salas){
       if(s.pelicula != null){
        System.out.println("sala: "+s.numero);
        System.out.println("titulo: "+ s.pelicula.titulo);
        System.out.println("duracion: " + s.pelicula.duracion);
        System.out.println("precio: "+ s.pelicula.precio);
        System.out.println("vendidas: "+ s.pelicula.cantidad);
       } else{
        System.out.println("sala: "+s.numero+ "no hay pelculas cargadas");
       }
    }
}

boolean cambiarprecio(int numero, double nuevoprecio){
    if(nuevoprecio <= 0) return false;
    for(Sala s : salas){
        if(s.numero == numero && s.pelicula != null){
            s.pelicula.precio = nuevoprecio; 
            return true;
        }
    }
    return false;
}
void recaudacionTotal(){
    double total = 0;
    for(Sala s : salas){
        if(s.pelicula != null){
            double rec = s.pelicula.recaudacion();
            total += rec;
            System.out.println("recaudacion sala: "+s.numero);
        }
    }
    System.out.println("recaudacion total: "+ total);
    
}

Double vendextitulo(String titulo, int cantidad){
    if(cantidad <= 0) return null;
    for(Sala s : salas){
        if(s.pelicula != null && s.pelicula.titulo.equalsIgnoreCase(titulo)){
            return s.pelicula.vendidas(cantidad);
        }
    }
    return null;
}


}//cine


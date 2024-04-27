import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al buscador de peliculas con SWAPI");

        Scanner s = new Scanner(System.in);

        System.out.println("Escriba el número de la pelicula que quieres: ");


        ConsultaPelicula consulta = new ConsultaPelicula();

        try {
            var numeroDePelicula = s.nextInt();
            Pelicula pelicula = consulta.buscaPelicula(numeroDePelicula);
            System.out.println(pelicula);
            GeneradorArchivo generado = new GeneradorArchivo();
            generado.guardarJson(pelicula);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            System.out.println("Numero no encontrado");
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicación");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepcion.ErrorEnConversionDeDuracionException;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner s = new Scanner(System.in);
        String busqueda = null, direccion;
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();


        while(true){
            System.out.println("Escriba el nombre de la pelicula: ");
            busqueda = s.nextLine();

            if(busqueda.equalsIgnoreCase("salir")){
                break;
            }


            direccion = "https://www.omdbapi.com/?t=" + busqueda.replace(" ", "+") + "&apikey=555af605";
            try{
                //Request
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();


                //Response
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);


                TituloOmdb mitituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(mitituloOmdb);

                Titulo mititulo = new Titulo(mitituloOmdb);
                System.out.println(mititulo);

                titulos.add(mititulo);

            }catch (NumberFormatException e){
                System.out.println("Ocurrio un error!");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Ocurrio un error en la URI, verifique la dirección!");
                System.out.println(e.getMessage());
            }catch (ErrorEnConversionDeDuracionException e){
                System.out.println("Ocurrio un error inesperado");
                System.out.println(e.getMessage());
            }
        }

        System.out.println(titulos);

        FileWriter escritura = new FileWriter("titulos.txt");
        escritura.write(gson.toJson(titulos));
        escritura.close();




        System.out.println("Finalizo el ejecución del programa");


    }
}

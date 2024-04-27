package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Titulo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner s = new Scanner(System.in);

        String busqueda, direccion;

        System.out.println("Escriba el nombre de la pelicula: ");
        busqueda = s.nextLine();
        direccion = "https://www.omdbapi.com/?t=" + busqueda + "&apikey=555af605";

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

        Gson gson = new Gson();
        Titulo mititulo = gson.fromJson(json, Titulo.class);
        System.out.println(mititulo);


    }
}

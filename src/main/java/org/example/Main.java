package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        var juegos = readFromFile("videojuegos_retro.csv");
        juegos.forEach(System.out::println);
    }

    private static ArrayList<Juego> readFromFile(String filename) {
        var juegos = new ArrayList<Juego>();

        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line = br.readLine(); // titulos

            while (line != null) {
                line = br.readLine();
                if(line != null) {
                    String[] contenido = line.split(",");
                    Juego juego = new Juego();
                    juego.setTitulo(contenido[0]);
                    juego.setAño(Integer.parseInt(contenido[1]));
                    juego.setPlataforma(contenido[2]);
                    juego.setGenero(contenido[3]);
                    juego.setDesarrolladora(contenido[4]);
                    juego.setDescripcion(contenido[5]);
                    juegos.add(juego);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return juegos;
    }
}
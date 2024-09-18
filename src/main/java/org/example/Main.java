package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        var juegos = readFromFile("videojuegos_retro.csv");
        juegos.forEach(System.out::println);

        leerDesdeURL("https://cesurformacion.com");
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

    public static void copiarArchivo(String archivoEntrada, String archivoSalida) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine(); // Añade un salto de línea después de cada línea copiada
            }
            System.out.println("Archivo copiado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    public static void leerDesdeURL(String urlArchivo) throws IOException {

        URL url = new URL(urlArchivo);
        InputStreamReader isr = new InputStreamReader(url.openStream());
        BufferedReader lector = new BufferedReader( isr);

        String linea;
        while ((linea = lector.readLine()) != null) {
            System.out.println(linea);
        }

    }

    public static void leerArchivoCSV(String nombreArchivo) throws IOException {

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] valores = linea.split(",");
                for (String valor : valores) {
                    // To do...
                }
            }
        }
    }

    public static void leerArchivoJSON(String nombreArchivo) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Lee el archivo JSON y lo convierte en un objeto de la clase Juego
            Juego juego = objectMapper.readValue(new File(nombreArchivo), Juego.class);
            // List<Persona> personas = objectMapper.readValue(new File(nombreArchivo), new TypeReference<List<Persona>>() {});

            System.out.println(juego); // Imprime el objeto
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

    }

}
package com.isunican.proyectobase.utilities;

import com.isunican.proyectobase.model.*;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/*
------------------------------------------------------------------
    Clase con los metodos necesarios para
    parsear un stream de datos en formato JSON
    en este caso, un stream con gasolineras
------------------------------------------------------------------
*/

public class ParserJSONGasolineras {

    private ParserJSONGasolineras(){}

    /**
     * parseaArrayGasolineras
     *
     * Se le pasa un stream de datos en formato JSON que contiene gasolineras.
     * Crea un JsonReader para el stream de datos y llama a un método auxiliar que lo analiza
     * y extrae una lista de objetos Gasolinera que es la que se devuelve
     *
     * @param in Inputsream Stream de datos JSON
     * @return List<Gasolinera> Lista de objetos Gasolinera con los datos obtenidas tras parsear el JSON
     * @throws IOException
     */
    public static List<Gasolinera> parseaArrayGasolineras (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            List<Gasolinera> listaGasolinerasParseada= readArrayGasolineras(reader);
            reader.close(); //He cambiado el try-finally porque no capturaban una posible excepcion, era solo para que despues del return se cerrara el reader, yo he hecho lo mismo de forma correcta.
            return listaGasolinerasParseada; //La excepcion se sigue propagando
    }

    /**
     * readArrayGasolineras
     *
     * Se le pasa un objeto JsonReader con el stream de datos JSON a analizar.
     * Crea una lista de gasolineras.
     * Va leyendo elementos hasta encontrar la cabecera "ListaEESSPrecio"
     * ya que de ella cuelga el array de gasolineras.
     * Mientras haya elementos los analiza con un método auxiliar
     * que analiza los datos de una gasolinera concreta
     * y devuelve un objeto Gasolinera que añadimos a la lista de gasolineras.
     * Finalmente se devuelve la lista de gasolineras.
     *
     * @param in JsonReader Stream de datos JSON apuntando al comienzo del stream
     * @return List Lista de objetos Gasolinera con los datos obtenidas tras parsear el JSON
     * @throws IOException
     */
    public static List<Gasolinera> readArrayGasolineras (JsonReader reader) throws IOException { //La excepcion se propaga
        List<Gasolinera> listGasolineras = new ArrayList<>();

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            Log.d("ENTRA", "Nombre del elemento: "+name);
            if(name.equals("ListaEESSPrecio")){
                reader.beginArray();
                while (reader.hasNext()){
                    listGasolineras.add(readGasolinera(reader));
                }
                reader.endArray();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();
        return listGasolineras;
    }

    /**
     * readGasolinera
     *
     * Se le pasa un objeto JsonReader con el stream de datos JSON a analizar
     * que está apuntando a una gasolinera concreta.
     * Va procesando este stream buscando las etiquetas de cada elemento
     * que se desea extraer, como "Rótulo" o "Localidad"
     * y almacena la cadena de su valor en un atributo del tipo adecuado,
     * parseándolo a entero, doble, etc. si es necesario.
     * Una vez extraidos todos los atributos, crea un objeto Gasolinera con ellos
     * y lo devuelve.
     *
     * @param in JsonReader stream de datos JSON
     * @return Gasolinera Objetos Gasolinera con los datos obtenidas tras parsear el JSON
     * @throws IOException
     */
    public static Gasolinera readGasolinera (JsonReader reader) throws IOException { //La excepcion se propaga
        reader.beginObject();
        String rotulo = "";
        String localidad = "";
        String provincia = "";
        String direccion = "";
        int id = -1;
        double gasoleoA = 0.0;
        double gasoleoB = 0.0; //Incluyo el gasoleoB
        double sinplomo95 = 0.0;
        double latitud=0.0;
        double longitud=0.0;
        while(reader.hasNext()){
            String name = reader.nextName();

            if (name.equals("Rótulo") && reader.peek() != JsonToken.NULL) {
                rotulo = reader.nextString();
            }else if (name.equals("Localidad")) {
                localidad = reader.nextString();
            }else if(name.equals("Provincia")){
                provincia = reader.nextString();
            }else if(name.equals("IDEESS")){
                id = reader.nextInt();
            }else if(name.equals("Precio Gasoleo A") && reader.peek() != JsonToken.NULL) {
                gasoleoA = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasoleo B") && reader.peek() != JsonToken.NULL) { //He incluido la captura del gasoleo B ya que me hacia falta para listar las gasolineras por su valor.
                gasoleoB = parseDouble(reader.nextString().replace(",", "."));
                if(gasoleoB==-1.0 || gasoleoB==0.0){
                    gasoleoB=1000.0; //Cambio su valor a uno alto ya que al ordenar las gasolineras por el precio de menor a mayor, aquellas que no disponen del producto deben ir las ultimas.
                }
            }else if(name.equals("Precio Gasolina 95 E5") && reader.peek() != JsonToken.NULL) {
                sinplomo95 = parseDouble(reader.nextString().replace(",", "."));
            } else if(name.equals("Longitud (WGS84)") && reader.peek() != JsonToken.NULL) {
            longitud = parseDouble(reader.nextString().replace(",", "."));
            } else if(name.equals("Latitud") && reader.peek() != JsonToken.NULL) {
            latitud = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Dirección")){
                direccion = reader.nextString();
            }else{
                reader.skipValue();
            }

        }
        reader.endObject();
        return new Gasolinera(id,latitud,longitud,localidad,provincia,direccion,gasoleoA,gasoleoB, sinplomo95,rotulo); //Incluyo el gasoleoB
    }

    private static double parseDouble(String str) {
        if (str == null || str.isEmpty()) {
            return -1.0;
        } else {
            return Double.parseDouble(str.replace(",", "."));
        }
    }
}
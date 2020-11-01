package com.isunican.proyectobase.utilities;

import android.util.JsonReader;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.fail;

public class ParserJSONGasolineras  {

    public JsonReader obtencionDelReader(String direccion){
        JsonReader reader=null;
        try {
            URL url = new URL(direccion);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.addRequestProperty("Accept", "application/json");
            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
            reader= new JsonReader(new InputStreamReader(in, "UTF-8"));

        }catch (IOException e){
            fail("Error al cargar informacion de la API");
        }
        return reader;
    }

    @Test
    public void readGasolineraTest(){
        
    }
}

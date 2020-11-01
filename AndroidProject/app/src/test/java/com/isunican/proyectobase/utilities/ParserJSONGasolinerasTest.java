//package com.isunican.proyectobase.utilities;
//
//import android.util.JsonReader;
//
//import com.isunican.proyectobase.model.Gasolinera;
//import com.isunican.proyectobase.presenter.PresenterGasolineras;
//
//import org.junit.Test;
//
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import static com.isunican.proyectobase.presenter.PresenterGasolineras.URL_GASOLINERAS_CANTABRIA;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//public class ParserJSONGasolinerasTest {
//
//    JsonReader lectorJson;
//    PresenterGasolineras presenter=new PresenterGasolineras();
//    Gasolinera gasolinera;
//
//    public JsonReader obtencionDelReader(String direccion){
//        JsonReader reader=null;
//        try {
//            URL url = new URL(direccion);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.addRequestProperty("Accept", "application/json");
//            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            reader= new JsonReader(new InputStreamReader(in, "UTF-8"));
//
//        }catch (IOException e){
//            fail("Error al cargar informacion de la API");
//        }
//        return reader;
//    }
//
//    @Test
//    public void readGasolineraTest(){
//
//        //UPJG.1a Se crea un JsonReader con una direccion de las de la API de gasolineras y se leera la primera.
//        lectorJson=obtencionDelReader(URL_GASOLINERAS_CANTABRIA);
//        try {
//            ParserJSONGasolineras.readGasolinera(lectorJson);
//            presenter.cargaDatosGasolineras();
//            gasolinera=new Gasolinera(06,"NOVALES","CANTABRIA","CARRETERA 6316 KM. 10,5",1.069,0.879, 1.179,"CEPSA");
//            assertEquals(presenter.getGasolineras().get(0),gasolinera);
//        }catch(IOException e){
//
//        }
//
//        //UPJG.1b Se crea un JsonReader con una direccion de una API que no sea de gasolineras, que sea de autobuses por ejemplo.
//        lectorJson=obtencionDelReader("http://datos.santander.es/api/rest/datasets/lineas_bus.json");
//        try {
//            ParserJSONGasolineras.readGasolinera(lectorJson);
//            presenter.cargaDatosGasolineras();
//
//            String rotulo = "";
//            String localidad = "";
//            String provincia = "";
//            String direccion = "";
//            int id = -1;
//            double gasoleoA = 0.0;
//            double gasoleoB = 0.0; //Incluyo el gasoleoB
//            double sinplomo95 = 0.0;
//
//            gasolinera=new  Gasolinera(id,localidad,provincia,direccion,gasoleoA,gasoleoB, sinplomo95,rotulo);
//            assertEquals(presenter.getGasolineras().get(0),gasolinera);
//        }catch(IOException e){
//
//        }
//
//
//
//
//        //UPJG.1c No se ha podido obtener una gasolinera, el puntero JsonReader apunta a null.
//        try{
//            ParserJSONGasolineras.readGasolinera(null);
//            fail();
//        }catch(IOException e){
//
//        }catch(NullPointerException e){
//
//        }
//
//    }
//}

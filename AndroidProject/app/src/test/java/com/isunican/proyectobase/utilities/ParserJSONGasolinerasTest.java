package com.isunican.proyectobase.utilities;

import android.os.Build;
import android.util.JsonReader;
import android.util.Log;
import com.isunican.proyectobase.model.Gasolinera;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class ParserJSONGasolinerasTest {


    private JsonReader reader;
    private String datos;
    private Gasolinera gasolinera;

    @Test
    public void readGasolineraTest(){

        //UPJG.1a Se crea un JsonReader al que se le pasa un string que simula un JSON correcto para una gasolinera .

        try {

            datos="{\n" +
                    "\"C.P.\": \"39526\",\n" +
                    "\"Dirección\": \"CARRETERA 6316 KM. 10,5\",\n" +
                    "\"Horario\": \"L-D: 08:00-21:00\",\n" +
                    "\"Latitud\": \"43,395944\",\n" +
                    "\"Localidad\": \"NOVALES\",\n" +
                    "\"Longitud (WGS84)\": \"-4,155194\",\n" +
                    "\"Margen\": \"I\",\n" +
                    "\"Municipio\": \"Alfoz de Lloredo\",\n" +
                    "\"Precio Biodiesel\": \"\",\n" +
                    "\"Precio Bioetanol\": \"\",\n" +
                    "\"Precio Gas Natural Comprimido\": \"\",\n" +
                    "\"Precio Gas Natural Licuado\": \"\",\n" +
                    "\"Precio Gases licuados del petróleo\": \"\",\n" +
                    "\"Precio Gasoleo A\": \"1,069\",\n" +
                    "\"Precio Gasoleo B\": \"0,879\",\n" +
                    "\"Precio Gasoleo Premium\": \"1,129\",\n" +
                    "\"Precio Gasolina 95 E10\": \"\",\n" +
                    "\"Precio Gasolina 95 E5\": \"1,179\",\n" +
                    "\"Precio Gasolina 95 E5 Premium\": \"\",\n" +
                    "\"Precio Gasolina 98 E10\": \"\",\n" +
                    "\"Precio Gasolina 98 E5\": \"1,349\",\n" +
                    "\"Precio Hidrogeno\": \"\",\n" +
                    "\"Provincia\": \"CANTABRIA\",\n" +
                    "\"Remisión\": \"dm\",\n" +
                    "\"Rótulo\": \"CEPSA\",\n" +
                    "\"Tipo Venta\": \"P\",\n" +
                    "\"% BioEtanol\": \"0,0\",\n" +
                    "\"% Éster metílico\": \"0,0\",\n" +
                    "\"IDEESS\": \"1039\",\n" +
                    "\"IDMunicipio\": \"5744\",\n" +
                    "\"IDProvincia\": \"39\",\n" +
                    "\"IDCCAA\": \"06\"\n" +
                    "}";
            reader=new JsonReader(new StringReader(datos));
            gasolinera=new Gasolinera(1039,"NOVALES","CANTABRIA","CARRETERA 6316 KM. 10,5",1.069,0.879, 1.179,"CEPSA");
            assertEquals(ParserJSONGasolineras.readGasolinera(reader),gasolinera);
        }catch(IOException e){

        }

        //UPJG.1b Se crea un JsonReader al que se le pasa un string que simula un JSON incorrecto para una gasolinera.

        try {
            datos="{\n" +
                    "\"C.P.\": \"39526\",\n" +
                    "\"Direcc\": \"CARRETERA 6316 KM. 10,5\",\n" +
                    "\"Horario\": \"L-D: 08:00-21:00\",\n" +
                    "\"Latitud\": \"43,395944\",\n" +
                    "\"Localidud\": \"NOVALES\",\n" +
                    "\"Longitud (WGS84)\": \"-4,155194\",\n" +
                    "\"Margen\": \"I\",\n" +
                    "\"Municipio\": \"Alfoz de Lloredo\",\n" +
                    "\"Precio Biodiesel\": \"\",\n" +
                    "\"Precio Bioetanol\": \"\",\n" +
                    "\"Precio Gas Natural Comprimido\": \"\",\n" +
                    "\"Precio Gas Natural Licuado\": \"\",\n" +
                    "\"Precio Gases licuados del petróleo\": \"\",\n" +
                    "\"Precio Gasoleu A\": \"1,069\",\n" +
                    "\"Precio Gasoleo B\": \"0,879\",\n" +
                    "\"Precio Gasoleo Premium\": \"1,129\",\n" +
                    "\"Precio Gasolina 95 E10\": \"\",\n" +
                    "\"Precio Gasolina 95 E5\": \"1,179\",\n" +
                    "\"Precio Gasolina 95 E5 Premium\": \"\",\n" +
                    "\"Precio Gasolina 98 E10\": \"\",\n" +
                    "\"Precio Gasolina 98 E5\": \"1,349\",\n" +
                    "\"Precio Hidrogeno\": \"\",\n" +
                    "\"Provincia\": \"CANTABRIA\",\n" +
                    "\"Remisión\": \"dm\",\n" +
                    "\"Rótul\": \"CEPSA\",\n" +
                    "\"Tipo Venta\": \"P\",\n" +
                    "\"% BioEtanol\": \"0,0\",\n" +
                    "\"% Éster metílico\": \"0,0\",\n" +
                    "\"IDEESS\": \"1039\",\n" +
                    "\"IDMunicipio\": \"5744\",\n" +
                    "\"IDProvincia\": \"39\",\n" +
                    "\"IDCCAA\": \"06\"\n" +
                    "}";
            reader=new JsonReader(new StringReader(datos));
            String rotulo = "";
            String localidad = "";
            String provincia = "CANTABRIA";
            String direccion = "";
            int id = 1039;
            double gasoleoA = 0.0;
            double gasoleoB = 0.879; //Incluyo el gasoleoB
            double sinplomo95 = 1.179;

            gasolinera=new  Gasolinera(id,localidad,provincia,direccion,gasoleoA,gasoleoB, sinplomo95,rotulo);
            assertEquals(ParserJSONGasolineras.readGasolinera(reader),gasolinera);
        }catch(IOException e){

        }


        //UPJG.1c No se ha podido obtener una gasolinera, el puntero JsonReader apunta a null.
        try{
            ParserJSONGasolineras.readGasolinera(null);
            fail();
        }catch(IOException e){

        }catch(NullPointerException e){
            Log.d("error","Ha saltado el NullpointerException como debería de saltar");
        }

    }
}

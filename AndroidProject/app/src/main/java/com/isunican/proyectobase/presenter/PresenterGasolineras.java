package com.isunican.proyectobase.presenter;

import android.util.Log;
import android.widget.Toast;

import com.isunican.proyectobase.model.*;
import com.isunican.proyectobase.utilities.ParserJSONGasolineras;
import com.isunican.proyectobase.utilities.RemoteFetch;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
------------------------------------------------------------------
    Clase presenter con la logica de gasolineras
    Mantiene un objeto ListaGasolineras que es el que mantendrá
    los datos de las gasolineras cargadas en nuestra aplicación
    Incluye métodos para gestionar la lista de gasolineras y
    cargar datos en ella.
------------------------------------------------------------------
*/
public class PresenterGasolineras {

    private List<Gasolinera> gasolineras;
    private List<PuntoConocido> puntosPuntoConocidos;

    //URLs para obtener datos de las gasolineras
    //https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help
    public static final String URL_GASOLINERAS_SPAIN="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/";
    public static final String URL_GASOLINERAS_CANTABRIA="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/06";
    public static final String URL_GASOLINERAS_SANTANDER="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroMunicipio/5819";
    public static final String SANTANDER="Santander";

    /**
     * Constructor, getters y setters
     */
    public PresenterGasolineras(){
        gasolineras = new ArrayList<>();
        puntosPuntoConocidos=new ArrayList<>();
    }

    public List<Gasolinera> getGasolineras(){
        return gasolineras;
    }

    public void setGasolineras(List<Gasolinera> l) {
        this.gasolineras = l;
    }

    /**
     * Metodo privado que se utiliza para ordenar por precio el diesel más barato.
     * Para esto se utiliza la clase Collections con su metodo sort, cuya eficiencia
     * temporal es O(n log n). Este metodo ordena teniendo en cuenta una clave natural,
     * como digo el gasoleo B, y la clase sobre la cual se van a hacer las comparaciones,
     * Gasolinera, debe implementar la interfaz Comparable que pide implementar compareTo().
     */
    private void ordenarGasolinerasCargadasPorPrecioDeGasoleoB(){
        Collections.sort(gasolineras);
    }
    public void ordenarGasolinerasPorDistanciaAPuntoConocido(){
        Collections.sort(gasolineras,new OrdenarGasolinerasPorDistancia());
    }
    /**
     * cargaDatosGasolineras
     *
     * Carga los datos de las gasolineras en la lista de gasolineras de la clase.
     * Para ello llama a métodos de carga de datos internos de la clase ListaGasolineras.
     * En este caso realiza una carga de datos remotos dada una URL
     *
     * Habría que mejorar el método para que permita pasar un parámetro
     * con los datos a cargar (id de la ciudad, comunidad autónoma, etc.)
     *
     * @param
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosGasolineras() {
        return cargaDatosRemotos(URL_GASOLINERAS_CANTABRIA);
    }

    /**
     * cargaDatosDummy
     *
     * Carga en la lista de gasolineras varias gasolineras definidas a "mano"
     * para hacer pruebas de funcionamiento
     *
     * @param
     * @return boolean
     */
    public boolean cargaDatosDummy(){
        //int ideess, String localidad, String provincia, String direccion, double gasoleoA,double gasoleoB, double gasolina95, String rotulo
        this.gasolineras.add(new Gasolinera(1000,43.459783, -3.826178,SANTANDER,SANTANDER, "Av Valdecilla", 1.299,1.359,1.359,"AVIA"));
        this.gasolineras.add(new Gasolinera(1053,43.459783, -3.826178,SANTANDER,SANTANDER, "Plaza Matias Montero", 1.270,1.359,1.349,"CAMPSA"));
        this.gasolineras.add(new Gasolinera(420,43.459783, -3.826178,SANTANDER,SANTANDER, "Area Arrabal Puerto de Raos", 1.249,1.359,1.279,"E.E.S.S. MAS, S.L."));
        this.gasolineras.add(new Gasolinera(9564,43.459783, -3.826178,SANTANDER,SANTANDER, "Av Parayas", 1.189,1.359,1.269,"EASYGAS"));
        this.gasolineras.add(new Gasolinera(1025,43.459783, -3.826178,SANTANDER,SANTANDER, "Calle el Empalme", 1.259,1.359,1.319,"CARREFOUR"));
        return true;
    }

    /**
     * Carga una lista de puntos conocidos a mano para hacer pruebas de funcionamiento
     * @return
     */
    public boolean cargarCoordenadasDummy(){
        this.puntosPuntoConocidos.add(new PuntoConocido("Mi casa",43.459783, -3.826178));
        this.puntosPuntoConocidos.add(new PuntoConocido("Trabajo",43.349337, -4.051923));
        this.puntosPuntoConocidos.add(new PuntoConocido("Colegio",43.477803, -3.792442));
        this.puntosPuntoConocidos.add(new PuntoConocido("Pueblo",43.334737, -3.549662));
        this.puntosPuntoConocidos.add(new PuntoConocido("Gimnasio",43.483004, -3.791504));
        return true;
    }

    public List<String> mostrarEtiquetasCoordenadas(){
        List<String> etiquetasCoordenadas=new ArrayList<>();
        for(PuntoConocido punto:puntosPuntoConocidos){
            etiquetasCoordenadas.add(punto.getEtiquetaCoordenada());
        }
        return etiquetasCoordenadas;
    }

    private double calcularDistanciaEntrePuntoYGasolinera(double lon1, double lat1, double lon2, double lat2){

            double radioTerrestre = 6371.0; // km

            lat1 = Math.toRadians(lat1);
            lon1 = Math.toRadians(lon1);
            lat2 = Math.toRadians(lat2);
            lon2 = Math.toRadians(lon2);

            double dlon = (lon2-lon1);
            double dlat = (lat2-lat1);

            double sinlat = Math.sin(dlat / 2);
            double sinlon = Math.sin(dlon / 2);

            double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlon*sinlon);
            double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

            double distanciaEnKilometros =  Math.floor(radioTerrestre * c * 10) / 10; // se trunca a un decimal
            return distanciaEnKilometros;
    }
    private PuntoConocido buscarCoordenadaPorEtiqueta(String etiqueta){
        PuntoConocido salida=null;
        int i=0;
        boolean salirBucle=false;
        while(i<puntosPuntoConocidos.size()|| !salirBucle){
            if(puntosPuntoConocidos.get(i).getEtiquetaCoordenada().equals(etiqueta)){
                salida=puntosPuntoConocidos.get(i);
                salirBucle=true;
            }
            i++;
        }
        return salida;
    }
    public void anhadirDistanciaEntrePuntoYGasolineras(String etiquetaPuntoConocido){
       try {
           PuntoConocido punto = buscarCoordenadaPorEtiqueta(etiquetaPuntoConocido);
           double latitudPunto=punto.getLatitud();
           double longitudPunto=punto.getLongitud();
           double distancia=0.0;
           for(Gasolinera gasolinera:gasolineras){
               distancia=calcularDistanciaEntrePuntoYGasolinera(longitudPunto,latitudPunto,gasolinera.getLongitud(),gasolinera.getLatitud());
               gasolinera.setDistanciaEntreGasolineraYPunto(distancia);
           }

       }catch(NullPointerException e){
           System.out.println("Puntero a null en el metodo anhadirDistanciaEntrePuntoYGasolineras()");
        }
    }

    /**
     * cargaDatosLocales
     *
     * A partir de la dirección de un fichero JSON pasado como parámetro:
     * Parsea la información para obtener una lista de gasolineras.
     * Finalmente, dicha lista queda almacenada en la clase.
     *
     * @param fichero Nombre del fichero
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosLocales(String fichero){
        return(fichero != null);
    }

    /**
     * cargaDatosRemotos
     *
     * A partir de la dirección pasada como parámetro:
     * Utiliza RemoteFetch para cargar el fichero JSON ubicado en dicha URL
     * en un stream de datos.
     * Luego utiliza ParserJSONGasolineras para parsear dicho stream
     * y extraer una lista de gasolineras.
     * Finalmente, dicha lista queda almacenada en la clase.
     *
     * @param direccion Dirección URL del JSON con los datos
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosRemotos(String direccion){ //Aqui se captura la posible excepcion que de el RemoteFetch, voy a cambiar la captura de una excepcion generica
        try {
            BufferedInputStream buffer = RemoteFetch.cargaBufferDesdeURL(direccion);
            gasolineras = ParserJSONGasolineras.parseaArrayGasolineras(buffer);
            ordenarGasolinerasCargadasPorPrecioDeGasoleoB(); // Llamo al metodo privado para ordenar segun la clave natural gasoleoB.
            Log.d("ENTRA", "Obten gasolineras:" + gasolineras.size());
            return true;
        } catch (Exception e) {
            Log.e("ERROR", "Error en la obtención de gasolineras: " + e.getMessage());
            return false;
        }
    }
}

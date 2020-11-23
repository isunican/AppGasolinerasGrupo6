package com.isunican.proyectobase.presenter;

import com.isunican.proyectobase.model.PuntoConocido;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Clase de prueba para verificar que el comportamiento de sus metodos es el esperado. Para detectar posibles errores.
 */
public class PresenterGasolinerasTest {


    private static PresenterGasolineras presenterGasolineras; //Puntero de la clase que contiene el metodo buscarCoordenadaPorEtiqueta() que vamos a probar
    private static PuntoConocido puntoConocido;

    /**
     * Metodo que inicializa los valores por defecto de los atributos necesarios para las pruebas.
     */
    @BeforeClass
    public static void setUp(){
        presenterGasolineras=new PresenterGasolineras();
        presenterGasolineras.cargarCoordenadasDummy();
        puntoConocido=presenterGasolineras.getPuntosPuntoConocido().get(3);
    }

    /**
     * Metodo de prueba que comprueba el correcto funcionamiento del metodo buscarCoordenadaPorEtiqueta() ante diferentes casos de prueba. Sirve para detertar errores en el metodo.
     */
    @Test
    public void buscarCoordenadaPorEtiquetaTest(){

        //Caso de prueba UPG.1a : La etiqueta que se pasa como parametro al metodo buscarCoordenadaPorEtiqueta() se encuentra en algun PuntoConocido.
            assertEquals(puntoConocido,presenterGasolineras.buscarCoordenadaPorEtiqueta(presenterGasolineras.getPuntosPuntoConocido().get(3).getEtiquetaCoordenada()));

        //Caso de prueba UPG.1b : La etiqueta que se pasa como parametro al metodo buscarCoordenadaPorEtiqueta() no se encuentra en ningún PuntoConocido.
            assertEquals(null,presenterGasolineras.buscarCoordenadaPorEtiqueta("abcdefg"));

        //Caso de prueba UPG.1c : La etiqueta que se pasa como parametro al metodo buscarCoordenadaPorEtiqueta() es null por lo que no se encuentra ningun PuntoConocido.
            assertEquals(null,presenterGasolineras.buscarCoordenadaPorEtiqueta(null));
    }
}

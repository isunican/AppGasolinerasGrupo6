package com.isunican.proyectobase.model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Clase de prueba para verificar que el comportamiento de sus metodos es el esperado. Para detectar posibles errores.
 */
public class OrdenarGasolinerasPorDistanciaTest {

    private static OrdenarGasolinerasPorDistancia ordenar; //Puntero de la clase que contiene el metodo compare() que vamos a probar
    private static Gasolinera gasolinera2;
    private static Gasolinera gasolinera1;


    /**
     * Metodo que inicializa los valores por defecto de los atributos necesarios para las pruebas.
     */
    @BeforeClass
    public static void setUp(){

        ordenar=new OrdenarGasolinerasPorDistancia();
        //Los siguientes datos no se tienen que corresponder con distancias reales para probar que el metodo compare() responde correctamente.
        gasolinera1=new Gasolinera(1234,43.23232,-4.232321,"Santander","Cantabria","Valdecilla 13",1.4,0.58,1.8,"Repsol");
        gasolinera1.setDistanciaEntreGasolineraYPunto(7.5); //Distancia de la gasolinera al punto de referencia ficticia.
        gasolinera2=new Gasolinera(1234,43.23232,-5.232321,"Santander","Cantabria","AV Castros 53",1.4,0.58,1.8,"Petronor");
        gasolinera2.setDistanciaEntreGasolineraYPunto(7.5); //Distancia de la gasolinera al punto de referencia ficticia.
    }

    /**
     * Metodo de prueba que comprueba el correcto funcionamiento del metodo compare() ante diferentes casos de prueba. Sirve para detertar errores en el metodo.
     */
    @Test
    public void compareTest(){
        //Caso de prueba UOGPD.1a: La distancia a la que se encuentra la gasolinera1 y la gasolinera2 a un punto de referencia es la misma.
            assertEquals(0,ordenar.compare(gasolinera1,gasolinera2)); //Al usar el metodo compare para ordenar, la gasolinera1 y la 2 podrian estar una antes que la otra indistintamente.

        //Caso de prueba UOGPD.1b: La distancia a la que se encuentra la gasolinera1 es menor que a la que se encuentra la gasolinera2 del punto de referencia.
            gasolinera1.setDistanciaEntreGasolineraYPunto(6.5);
            assertEquals(-1,ordenar.compare(gasolinera1,gasolinera2)); //Al usar el metodo compare para ordenar, la gasolinera1 iria antes que la gasolinera2.

        //Caso de prueba UOGPD.1c: La distancia a la que se encuentra la gasolinera1 es mayor que a la que se encuentra la gasolinera2 del punto de referencia.
            gasolinera1.setDistanciaEntreGasolineraYPunto(8.5);
            assertEquals(1,ordenar.compare(gasolinera1,gasolinera2)); //Al usar el metodo compare para ordenar, la gasolinera1 iria despues que la gasolinera2.

        //Caso de prueba UOGPD.1d: El puntero gasolinera1 es a null.
            try{
                ordenar.compare(null,gasolinera2); //Al usar el metodo compare saltaria una excepcion NullPointerException por intentar usar los metodos de un puntero a null (gasolinera1).
                fail();
            }catch(NullPointerException e){

            }

        //Caso de prueba UOGPD.1e: El puntero gasolinera2 es a null.
        try{
            ordenar.compare(gasolinera1,null); //Al usar el metodo compare saltaria una excepcion NullPointerException por intentar usar los metodos de un puntero a null (gasolinera2).
            fail();
        }catch(NullPointerException e){

        }

    }

}

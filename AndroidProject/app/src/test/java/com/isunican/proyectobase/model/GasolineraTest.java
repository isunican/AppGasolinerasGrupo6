package com.isunican.proyectobase.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GasolineraTest {

    Gasolinera gasolineraBase=new Gasolinera(1000,"Santander","Santander", "Av Valdecilla", 1.299,1.359,1.359,"AVIA");
    Gasolinera gasolineraAComparar;

    @Test
    public void compareToTest(){

        //Caso UG.1a Gasolinera con atributo gasoleoB con precio igual a la gasolineraBase, es decir, gasolineraBase.compareTo(gasolineraAComparar)==0
        gasolineraAComparar=new Gasolinera(1212,"Santander","Santander", "Av Castros", 1.12,1.359,1.02,"REPSOL");
        assertEquals(0,gasolineraBase.compareTo(gasolineraAComparar));

        //Caso UG.1b Gasolinera (gasolineraBase) con atributo gasoleoB con precio menor a la gasolineraAComparar , es decir, gasolineraBase.compareTo(gasolineraAComparar)==-1
        gasolineraAComparar=new Gasolinera(1212,"Santander","Santander", "Av Castros", 1.12,1.360,1.02,"PETRONOR");
        assertEquals(-1,gasolineraBase.compareTo(gasolineraAComparar));

        //Caso UG.1c Gasolinera (gasolineraBase) con atributo gasoleoB con precio mayor a la gasolineraAComparar , es decir, gasolineraBase.compareTo(gasolineraAComparar)==1
        gasolineraAComparar=new Gasolinera(1212,"Santander","Santander", "Av Castros", 1.12,1.358,1.02,"SPREEFEF");
        assertEquals(1,gasolineraBase.compareTo(gasolineraAComparar));

        //Caso UG.1d Gasolinera (gasolineraBase) con atributo gasoleoB a la que se pasa a su metodo compareTo() un puntero nulo como parametro produciendo una NullPointerException
        gasolineraAComparar=null;
        try{
            gasolineraBase.compareTo(gasolineraAComparar);
            fail();
        }catch (NullPointerException e){

        }
    }
}

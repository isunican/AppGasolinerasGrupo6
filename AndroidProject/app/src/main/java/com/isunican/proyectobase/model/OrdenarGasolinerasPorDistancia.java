package com.isunican.proyectobase.model;

import java.util.Comparator;

/**
 * Clase que implementa la interfaz Comparator lo cual implica que al definir el metodo compare() podremos ordenar elementos de una lista por un valor diferente al natural, en este caso por la distancia
 * a la que están las diferentes gasolineras a un punto de referencia indicado por coordenadas.
 */
public class OrdenarGasolinerasPorDistancia implements Comparator<Gasolinera> {
    /**
     * Metodo que recibe como parametro dos objetos del mismo tipo y compara el mismo atributo para los dos objetos, con el fin de determinar el orden en una lista
     * @param o1
     * @param o2
     * @return int
     */
    @Override
    public int compare(Gasolinera o1, Gasolinera o2) {
        int salida;
        if(o1.getDistanciaEntreGasolineraYPunto()<o2.getDistanciaEntreGasolineraYPunto()){
            salida=-1; //El objeto o1 estara antes que el o2 en el orden
        }else if(o1.getDistanciaEntreGasolineraYPunto()==o2.getDistanciaEntreGasolineraYPunto()){
            salida=0;  //El objeto o1 y el o2 estarán indistintamente uno antes o despues que el otro.
        }else{
            salida=1;//El objeto o1 estara despues que el o2 en el orden
        }
        return salida;
    }
}
